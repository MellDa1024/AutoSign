# AutoSign
ctrl c + ctrl v(ed) code from salhack's [autosign module](https://github.com/ionar2/spidermod/blob/master/src/main/java/me/ionar/salhack/module/misc/AutoSignModule.java) and modified it for lambda.  

modifing text with command to bypass unicode charater because unicode character didin't work in salhack.

rusherhack worked with changing value in autosign.json but it needed to restart the client :(
## How To Use it
;autosign set <offset(1~4)> <text(string)>  
-> sets sign's < offset > line to < text >  
example: ;autosign set 1 "☯ＭｅｌｌＤａ☯"  

;autosign steal <offset(1~4)>  
-> set sign's < offset > to player currentitem's displayname.  
place sign, wait for a sec, then press done.