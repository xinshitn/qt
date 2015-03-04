/**
 * Created by VicenTN on 14-5-18.
 */
function showNumberWithAnimation(i,j,randNumber) {
    var numberCell = $("#number-cell-"+i+"-"+j);

    numberCell.css("background-color",getNumberBackgroundColor(randNumber));
    numberCell.css("color",getNumberColor(randNumber));
    numberCell.text(randNumber);

    numberCell.animate({
        width:cellSideLength,
        height:cellSideLength,
        top:getPosTop(i,j),
        left:getPosLeft(i,j)
    },50);
}

function showMoveAnimation(formx,formy,tox,toy){
    var numberCell = $("#number-cell-"+formx+"-"+formy);
    numberCell.animate({
        top:getPosTop(tox,toy),
        left:getPosLeft(tox,toy)
    },200);
}

function nomove(board){
    if( canMoveLeft(board) ||
        canMoveUp(board) ||
        canMoveRight(board) ||
        canMoveDown(board)){
        return false;
    }
    return true;
}

function updateScore(score){
    $("#score").html(score+"");
    if(typeof frushScore!='undefined'&frushScore instanceof Function){
    	frushScore ();
    }
}