package tk.patsite.Patserverdiscordbot;

/*
MIT License

Copyright (c) 2021 PatrickMSM-Chertes

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
 */

import net.dv8tion.jda.api.entities.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

import static tk.patsite.Patserverdiscordbot.Settings.AuthSettings.VERIFY_EMOJI;
import static tk.patsite.Patserverdiscordbot.Settings.AuthSettings.VERIFY_MESSAGE;
import static tk.patsite.Patserverdiscordbot.Settings.NonSettings.executor;


public final class UserAuthenticator {

    private static final Set<String> VERIFIED_CACHE = new HashSet<>();

    public final void reactAuth(Member user) {
        onReactCheck(user).thenAccept(correct -> {
            if (correct)
                accept(user);
        });
    }

    public final void roleAuth(Member user, List<Role> roles) {
       onRoleCheck(user, roles).thenAccept(correct -> {
           if (correct)
               accept(user);
       });
    }



    private void accept(Member user) {
        Guild userG = user.getGuild();

        userG.addRoleToMember(user, userG.getRoleById(Settings.AuthSettings.MEMBER_ROLE)).queue();
        userG.removeRoleFromMember(user, userG.getRoleById(Settings.AuthSettings.PAUTH_ROLE)).queue();
    }








    private static boolean checkCache(Member user) {
        return VERIFIED_CACHE.contains(user.getId());
    }
    private static void setCache(Member user) {
        VERIFIED_CACHE.add(user.getId());
    }



    public final CompletableFuture<Boolean> gotPauthRoleNoReact(Member user) {
        CompletableFuture<Boolean> future = new CompletableFuture<>();
        executor.execute(() -> {
            // check the react
            for (User user1 : user.getGuild().getTextChannelById(Settings.AuthSettings.VERIFY_CHANNEL).retrieveReactionUsersById(VERIFY_MESSAGE,VERIFY_EMOJI)) {
                if (user1.getId().equals(user.getId())) {
                    setCache(user);
                    future.complete(false);
                    return;
                }
            }

            // check the role
            for (Role role : user.getRoles()) {
                if (Settings.AuthSettings.PAUTH_ROLE == role.getIdLong()) {
                    setCache(user);
                    future.complete(true);
                    return;
                }
            }


            future.complete(false);
        });
        return future;
    }

    private CompletableFuture<Boolean> onReactCheck(Member user) {
        CompletableFuture<Boolean> future = new CompletableFuture<>();
        executor.execute(() -> {
            // Check cache
            if (checkCache(user)) {
                future.complete(true);
                return;
            }

            // Check if the user reacted to the correct message
            for (User user1 : user.getGuild().getTextChannelById(Settings.AuthSettings.VERIFY_CHANNEL).retrieveReactionUsersById(VERIFY_MESSAGE,VERIFY_EMOJI)) {
                if (!user1.getId().equals(user.getId())) {
                    future.complete(false);
                    return;
                }
            }


            // The user already reacted.
            // Check if he actually has the role,

            for(Role role : user.getRoles()) {
                if (role.getIdLong() == Settings.AuthSettings.PAUTH_ROLE) {
                    setCache(user);
                    future.complete(true);
                    return;
                }
            }
            future.complete(false);
        });
        return future;
    }


    private CompletableFuture<Boolean> onRoleCheck(Member user, List<Role> roles) {
        CompletableFuture<Boolean> future = new CompletableFuture<>();
        executor.execute(() -> {
            // Check cache
            if (checkCache(user)) {
                future.complete(true);
                return;
            }


            // Check if the user got the correct role
            for (Role role2 : roles) {
                if (Settings.AuthSettings.PAUTH_ROLE != role2.getIdLong()) {
                    future.complete(false);
                    return;
                }
            }


            // The user already has the role.
            // Check if he actually has reacted.


            for (User user1 : user.getGuild().getTextChannelById(Settings.AuthSettings.VERIFY_CHANNEL).retrieveReactionUsersById(VERIFY_MESSAGE, VERIFY_EMOJI)) {
                if (user.getUser().getId().equals(user1.getId())) {
                    setCache(user);
                    future.complete(true);
                    return;
                }
            }

            future.complete(false);
        });
        return future;
    }
}
