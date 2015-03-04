/**
 * Created by VicenTN on 14-5-18.
 */
var board = new Array();
var score = 0;
var hasConflicted = new Array();

var startx = 0;
var starty = 0;
var endx = 0;
var endy = 0;

$(document).ready(function(){
	prepareForMobile();
    newgame();
});

function prepareForMobile(){

    if( documentWidth > 500 ){
        gridContainerWidth = 500;
        cellSpace = 20;
        cellSideLength = 100;
    }
    
    $('#grid-container').css('width',gridContainerWidth - 2*cellSpace);
    $('#grid-container').css('height',gridContainerWidth - 2*cellSpace);
    $('#grid-container').css('padding', cellSpace);
    $('#grid-container').css('border-radius',0.02*gridContainerWidth);

    $('.grid-cell').css('width',cellSideLength);
    $('.grid-cell').css('height',cellSideLength);
    $('.grid-cell').css('border-radius',0.02*cellSideLength);
}

function newgame(){
	//初始化得分
	$("#score").html("0");
    //初始化
    init();
    //在随机两个格子生产数字
    generateOneNumber();
    generateOneNumber();
}

function init(){
    for(var i=0; i<4;i++){
        for(var j=0; j<4;j++){
            var gridCell = $("#grid-cell-"+i+"-"+j);
            gridCell.css('top',getPosTop(i,j));
            gridCell.css('left',getPosLeft(i,j));
        }
    }

    for(var i=0; i<4; i++){
        board[i] = new Array();
        hasConflicted[i] = new Array();
        for( var j=0; j<4; j++){
            board[i][j] = 0;
            hasConflicted[i][j] = false;
        }
    }

    score = 0;
    
    updateBoardView();
}

function updateBoardView() {
    $(".number-cell").remove();
    for(var i=0; i<4; i++){
        for(var j=0; j<4; j++){
            $("#grid-container").append("<div class='number-cell' id='number-cell-"+i+"-"+j+"'></div>");
            var theNumberCell = $('#number-cell-'+i+'-'+j);

            if(board[i][j] == 0){
                theNumberCell.css('width','0px');
                theNumberCell.css('height','0px');
                theNumberCell.css('top',getPosTop(i,j)+cellSideLength/2);
                theNumberCell.css('left',getPosLeft(i,j)+cellSideLength/2);
            }else{
                theNumberCell.css('width',cellSideLength);
                theNumberCell.css('height',cellSideLength);
                theNumberCell.css('top',getPosTop(i,j));
                theNumberCell.css('left',getPosLeft(i,j));
                theNumberCell.css('background-color',getNumberBackgroundColor(board[i][j]));
                theNumberCell.css('color',getNumberColor(board[i][j]));
                theNumberCell.text(board[i][j]);
            }

            hasConflicted[i][j] = false;
        }
    }
    $('.number-cell').css('line-height',cellSideLength+'px');
    $('.number-cell').css('font-size',0.6*cellSideLength+'px');
}

function generateOneNumber(){
    if(nospace(board)){
        return false;
    }

    //寻找可用格子
    var index = 0;
    var zeroCell = new Array();
    for(var i=0;i<4;i++){
        for(var j=0;j<4;j++){
            if(board[i][j]==0){
                zeroCell[index] = [i,j];
                index++;
            }
        }
    }
    //在可用格子中随机挑选一个
    var ranCell = parseInt(Math.floor(Math.random()*index));
    var randx = zeroCell[ranCell][0];
    var randy = zeroCell[ranCell][1];
    //随机一个位置
//    var randx = parseInt(Math.floor(Math.random()*4));
//    var randy = parseInt(Math.floor(Math.random()*4));
//    while(true){
//        if(board[randx][randy] == 0){
//            break;
//        }
//        var randx = parseInt(Math.floor(Math.random()*4));
//        var randy = parseInt(Math.floor(Math.random()*4));
//    }
    //随机一个数字
    var randNumber = Math.random()<0.5 ? 2:4;
    //在随机位置显示随机数字
    board[randx][randy] = randNumber;
    showNumberWithAnimation(randx,randy,randNumber);

    return true;
}

$(document).keydown(function(event){
    switch (event.keyCode){
        case 37://left
            event.preventDefault();
			if(moveLeft()){
                setTimeout("generateOneNumber()",210);
                setTimeout("isgameover()",300);
            };
            break;
        case 38://up
            event.preventDefault();
			if(moveUp()){
                setTimeout("generateOneNumber()",210);
                setTimeout("isgameover()",300);
            }
            break;
        case 39://right
            event.preventDefault();
			if(moveRight()){
                setTimeout("generateOneNumber()",210);
                setTimeout("isgameover()",300);
            }
            break;
        case 40://down
            event.preventDefault();
			if(moveDown()){
                setTimeout("generateOneNumber()",210);
                setTimeout("isgameover()",300);
            }
            break;
        default :
            break;
    }
});

document.addEventListener('touchstart',function(event){
    startx = event.touches[0].pageX;
    starty = event.touches[0].pageY;
});

document.addEventListener('touchmove',function(event){
    event.preventDefault();
});

document.addEventListener('touchend',function(event){
    endx = event.changedTouches[0].pageX;
    endy = event.changedTouches[0].pageY;

    var deltax = endx - startx;
    var deltay = endy - starty;

    if( Math.abs( deltax ) < 0.15*documentWidth && Math.abs( deltay ) < 0.15*documentWidth )
        return;

    if( Math.abs( deltax ) >= Math.abs( deltay ) ){

        if( deltax > 0 ){
            //move right
            if( moveRight() ){
                setTimeout("generateOneNumber()",210);
                setTimeout("isgameover()",300);
            }
        }
        else{
            //move left
            if( moveLeft() ){
                setTimeout("generateOneNumber()",210);
                setTimeout("isgameover()",300);
            }
        }
    }
    else{
        if( deltay > 0 ){
            //move down
            if( moveDown() ){
                setTimeout("generateOneNumber()",210);
                setTimeout("isgameover()",300);
            }
        }
        else{
            //move up
            if( moveUp() ){
                setTimeout("generateOneNumber()",210);
                setTimeout("isgameover()",300);
            }
        }
    }
});

function isgameover(){
	if(typeof saveBoard!='undefined'&saveBoard instanceof Function){
		saveBoard();//保存残局
    }
    if(nospace(board) && nomove(board)){
        gameover();
    }
}

function gameover(){
	if(typeof updateTop!='undefined'&updateTop instanceof Function){
		updateTop ();
    }
    alert("游戏结束");
}

function moveLeft(){
    if(!canMoveLeft(board)){
        return false;
    }

    for(var i=0; i<4; i++){
        for(var j=0; j<4; j++){
            if(board[i][j] != 0){
                for(var k =0; k<j;k++){
                    if(board[i][k]==0&&noBlockHorizontal(i,k,j,board)){
                        //move
                        showMoveAnimation(i,j,i,k);
                        board[i][k] = board[i][j];
                        board[i][j] = 0;
                        continue;
                    }
                    else if(board[i][k] == board[i][j]&&noBlockHorizontal(i,k,j,board)&&!hasConflicted[i][k]){
                        //move
                        showMoveAnimation(i,j,i,k);
                        //add
                        board[i][k] += board[i][j];
                        board[i][j] = 0;
                        //add score
                        if(board[i][k]>score){
                            score = board[i][k];
                            updateScore(score);
                        }
                        hasConflicted[i][k] = true;
                        continue;
                    }
                }
            }
        }
    }
    setTimeout("updateBoardView()",200);
    return true;
}

function moveUp(){
    if(!canMoveUp(board)){
        return false;
    }

    for(var i=0; i<4; i++){
        for(var j=0; j<4; j++){
            if(board[i][j] != 0){
                for(var k =0; k<i;k++){
                    if(board[k][j]==0&&noBlockVertical(k,i,j,board)){
                        //move
                        showMoveAnimation(i,j,k,j);
                        board[k][j] = board[i][j];
                        board[i][j] = 0;
                        continue;
                    }
                    else if(board[k][j] == board[i][j]&&noBlockVertical(k,i,j,board)&&!hasConflicted[k][j]){
                        //move
                        showMoveAnimation(i,j,k,j);
                        //add
                        board[k][j] += board[i][j];
                        board[i][j] = 0;
                        //add score
                        if(board[k][j]>score){
                            score = board[k][j];
                            updateScore(score);
                        }
                        hasConflicted[k][j] = true;
                        continue;
                    }
                }
            }
        }
    }
    setTimeout("updateBoardView()",200);
    return true;
}

function moveRight(){
    if(!canMoveRight(board)){
        return false;
    }

    for(var i=3; i>=0; i--){
        for(var j=3; j>=0; j--){
            if(board[i][j] != 0){
                for(var k =3; k>j;k--){
                    if(board[i][k]==0&&noBlockHorizontal(i,j,k,board)){
                        //move
                        showMoveAnimation(i,j,i,k);
                        board[i][k] = board[i][j];
                        board[i][j] = 0;
                        continue;
                    }
                    else if(board[i][k] == board[i][j]&&noBlockHorizontal(i,j,k,board)&&!hasConflicted[i][k]){
                        //move
                        showMoveAnimation(i,j,i,k);
                        //add
                        board[i][k] += board[i][j];
                        board[i][j] = 0;
                        //add score
                        if(board[i][k]>score){
                            score = board[i][k];
                            updateScore(score);
                        }
                        hasConflicted[i][k] = true;
                        continue;
                    }
                }
            }
        }
    }
    setTimeout("updateBoardView()",200);
    return true;
}

function moveDown(){
    if(!canMoveDown(board)){
        return false;
    }

    for(var i=3; i>=0; i--){
        for(var j=3; j>=0; j--){
            if(board[i][j] != 0){
                for(var k =3; k>i;k--){
                    if(board[k][j]==0&&noBlockVertical(i,k,j,board)){
                        //move
                        showMoveAnimation(i,j,k,j);
                        board[k][j] = board[i][j];
                        board[i][j] = 0;
                        continue;
                    }
                    else if(board[k][j] == board[i][j]&&noBlockVertical(i,k,j,board)&&!hasConflicted[k][j]){
                        //move
                        showMoveAnimation(i,j,k,j);
                        //add
                        board[k][j] += board[i][j];
                        board[i][j] = 0;
                        //add score
                        if(board[k][j]>score){
                            score = board[k][j];
                            updateScore(score);
                        }
                        hasConflicted[k][j] = true;
                        continue;
                    }
                }
            }
        }
    }
    setTimeout("updateBoardView()",200);
    return true;
}