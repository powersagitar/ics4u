#!/bin/zsh

which bundle

if [[ $? -ne 0 ]]; then
  echo 'bundle (ruby) must be installed.'
fi

jekyll_config="docs/_config.yml"

if [[ ! -f "$jekyll_config" ]]; then
  echo 'Please run the script in `culminating-mastermind`.'
  exit 1
fi

cd docs

bundle install
bundle exec jekyll serve
