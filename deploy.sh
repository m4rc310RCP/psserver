#!/bin/bash
git add . && git commit -m 'Feito deploy para servidor dokku' && git push -f dokku master && git push -f origin master

