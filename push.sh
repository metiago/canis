#!/bin/sh

git init

git config user.email "tiagotg.ribeiro@gmail.com"
git config user.name "Tiago"

git add .

git commit -m "first commit"

git remote add origin https://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@github.com/metiago/canis.git

git push origin master