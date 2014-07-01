/**
 * 
 */
function hideAndShowNumIn(boxCount) {
    var numInputs = document.getElementsByClassName("anteValue");
    for (i = boxCount; i < numInputs.length; i++) {
        numInputs[i].style.display = 'none';
    }
    for (i = boxCount - 1; i >= 0; i--) {
        numInputs[i].style.display = 'block';
    }
}

function setPrevAnte(prevAnte) {
	var numInputs = document.getElementsByClassName("anteValue");
	var radioBoxCount = document.getElementsByName("boxCount");
	radioBoxCount[prevAnte.length - 1].checked = 'checked';
	for (i = 0; i < numInputs.length; i++) {
		if ( prevAnte.length > i) {
			numInputs[i].value = prevAnte[i];
		} else {
			
			hideAndShowNumIn(i);
			break;
		}
	}
}