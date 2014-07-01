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