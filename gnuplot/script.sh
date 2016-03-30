seq 5 | awk '{print 2*$1, $1*$1}'

seq 5 | awk '{print 2*$1, $1*$1}' | feedgnuplot --lines --points --legend 0 "data 0" --title "Test plot" --y2 1 --terminal 'dumb 80,40' --exit

while true; do sleep 1; cat /proc/net/dev; done |
    gawk '/wlan0/ {if(b) {print $2-b; fflush()} b=$2}' |
    feedgnuplot --lines --stream --xlen 10 --ylabel 'Bytes/sec' --xlabel seconds

