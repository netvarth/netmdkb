var timerId;
$j(document).ready(function () {
    //Clear timeout to hide beeper on mouseover
    //start timeout to hide beeper on mouseout
    $j("#BeeperBox").mouseenter(stopHide).mouseleave(startHide);

    $j('.beeper_x').click(function () {
        //hide the beeper when the close button on the beeper is clicked
        $j("#BeeperBox").hide();
    });

});
//function to display the beeper and hide it 
function showTip(message) {
	$j('#BeeperBox .UIBeep_Title').html(message);
	$j("#BeeperBox").show();
    timerId = setTimeout(function () {
        $j("#BeeperBox").hide();
    }, 5000);
}

//function to destroy the timeout
function stopHide() {
	clearTimeout(timerId);
}
	
//function to hide the beeper
function startHide() {
    timerId = setTimeout(function () {
		$j("#BeeperBox").hide();
    }, 5000);
}