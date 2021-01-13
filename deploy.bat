@echo off
set/p cName=Enter commit name:
git commit -am "%cname%"
git push heroku master
git push origin master
@echo on