#!/bin/bash

autodeploypath="/opt/glassfish3/glassfish/domains/testdomain/autodeploy"
repolist="DzikieSpawarki"


function deploy {
        for ws in `cat webapp_list.txt`; do
                echo ws $ws
                cd $ws
                mvn package
                [ -e target/*.war ] && cp target/*.war $autodeploypath
                [ -e */target/*.jar ] && cp */target/*.jar $autodeploypath
                echo package is deployed
                cd ..
        done
}


function checkrepo {
        for repo in $repolist; do
                cd $repo;
                echo repo $repo
                response=`git pull`
                echo response $response
                if [ "$response" != "Already up-to-date." ]; then
                        echo deploy
                        deploy
                fi
                cd ..
        done
}


while true; do
        checkrepo
        echo sleep
        sleep 10
done

