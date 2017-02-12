#!/bin/bash

# generate files
npm run scrape
cp -r static build

# push files
cd build
git init
git add .
git commit -m "gh-pages"
git remote add origin git@github.com:josepharhar/meleeauthority
git branch gh-pages
git push -f origin gh-pages

# clean files
cd ..
git clean -fdx build
rm -rf build/.git
