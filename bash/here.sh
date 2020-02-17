#!/bin/bash
cat << EndOFText
	This is a multiline
stringa and shoudl appear
	as such.
EndOFText

i=0
while [ $i -le 10 ]; do
	echo i: $i
	((i+=1))
done

j=0
until [ $j -ge 10 ]; do
	echo j: $j
	((j+=1))
done

ten=10
for ((k=1; k<=$ten; k+=1))
do
	echo k: $k
done

declare -A arr
arr["name"]="Ahsan"
arr["id"]="134"

for i in "${!arr[@]}"
do 
	echo "$i: ${arr[$i]}"
done
