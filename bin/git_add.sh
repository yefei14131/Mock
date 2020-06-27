#!/usr/bin/env bash

function addSubDir()
{
    for i in $(ls $1)
    do
        if [ -f $1/$i ]
        then
            if [ $i == pom.xml ];then  git add $1/$i; fi;
        elif [ -d $1/$i ]
        then
            if [ $i == src ]
            then
                git add $1/$i
            else
                addSubDir $1/$i
            fi
        fi
    done
}

basepath=$(cd `dirname $0`; pwd)
echo $basepath/..
addSubDir $basepath/..