#!/bin/bash
while true
do
  x=$((x+1))
  pwd >/dev/null
  if [ $x -gt 50000 ]
  then
    exit
  fi
done

