#!/usr/bin/env bash

mkdir tmp

templatePath="content/templates/"

for file in $templatePath*/ ; do
  if [[ -d "$file" && ! -L "$file" ]]; then
    dir=${file%*/}
    dir=${dir##*/}
    cp $templatePath$dir/schema.json "tmp/$dir.json"
  fi;
done