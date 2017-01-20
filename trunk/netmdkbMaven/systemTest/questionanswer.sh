result=$(curl -s -H "Accept: application/json" -H "Content-type: application/json; charset=UTF-8" -X POST --data @/var/lib/jenkins/jobs/workspace/netmdkb/systemTest/createquestans.txt "http://192.168.1.99:8080/netmdkb/analytics/qa/Obstetrics/Survey1" -b cookies.txt);
echo $result

curl -s -H "Accept: application/json" -H "Content-type: application/json; charset=UTF-8" -X POST --data @/var/lib/jenkins/jobs/workspace/netmdkb/systemTest/updatequestans.txt "http://192.168.1.99:8080/netmdkb/analytics/qa/Obstetrics/Survey1" -b cookies.txt


curl -s -H "Accept: application/json" -H "Content-type: application/json; charset=UTF-8" -X POST --data @/var/lib/jenkins/jobs/workspace/netmdkb/systemTest/deletequestans.txt "http://192.168.1.99:8080/netmdkb/analytics/qa/Obstetrics/Survey1" -b cookies.txt