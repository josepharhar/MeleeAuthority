#!/bin/bash

# generate files
mkdir -p build
npm run scrape
cp -r static/* build

# push files
cd build
git init
git add .
git commit -m "gh-pages"
git remote add origin git@github.com:josepharhar/meleeauthority
git branch gh-pages
git push -f origin gh-pages
cd ..

# clean files
git clean -fdx build
rm -rf build/.git
