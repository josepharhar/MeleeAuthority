#!/bin/bash

# generate files
mkdir -p build
npm run scrape
cp -r static build
cp -r fonts build
cp -r client-build build
mkdir -p build/json
cp json/*.json build/json

# push files
cd build
git init
git add .
git commit -m "gh-pages"
#git remote add origin git@github.com:josepharhar/meleeauthority
#git branch gh-pages
#git push -f origin gh-pages
git remote add origin git@github.com:josepharhar/josepharhar.github.io
#git branch master
git push -f origin master
cd ..

# clean files
git clean -fdx build
rm -rf build/.git
