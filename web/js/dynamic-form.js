/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

    var questionCounter = 0;
    var textQuestionCounter = 0;
    var radioQuestionCounter = 0;
    var checkQuestionCounter = 0;
    
    var radioAnswerCounter = new Array();
    var checkAnswerCounter = new Array();
$(document).ready(function() {
    
    
    $('#add-text-button').click(addTextQuestion);
    $('#add-radio-button').click(addRadioQuestion);
    $('#add-check-button').click(addCheckQuestion);
    
    
    function addTextQuestion(){
        textQuestionCounter++;
        questionCounter++;
        $('.question-container').append(`<div class="control-group">
                      <div class="form-group floating-label-form-group controls">
                        <label>Question-`+ questionCounter + `</label>
                        <input name='text-question-` + textQuestionCounter + `' type="text" class="form-control" placeholder="Add survey name" id="text-question-` + textQuestionCounter + `" required data-validation-required-message="Please enter your Question." >
                      </div>
                    </div>`);
    }
    
    function addRadioQuestion(){
        
        radioQuestionCounter++;
        questionCounter++;
        radioAnswerCounter.push(0);
        alert(radioAnswerCounter);
        $('.question-container').append(
                    `<div class="control-group">
                      <div class="form-group floating-label-form-group controls">
                        <label>Question-`+ questionCounter + `</label>
                        <input name='radio-question-` + radioQuestionCounter + `' type="text" class="form-control" placeholder="Add Radio Question" id="radio-question-` + radioQuestionCounter + `" required data-validation-required-message="Please enter your Question." >
                      </div>
                    </div>
                    <div class="radio-answer-container-` + radioQuestionCounter + `">
                        
                    </div>
        <br>
                    <button type='button' class='radio-answer-button btn btn-warning' id='radio-answer-button-` + radioQuestionCounter +`'>Add Answer</button>
                    
           <br> `
                );
        
        $('#script-div').remove();
        alert('deleted');
        $('body').append(`<div id="script-div">
            <script src="js/sub-dynamic-form.js" type="text/javascript"></script>
        </div>`);
                
    }
    function addCheckQuestion(){
        
        checkQuestionCounter++;
        questionCounter++;
        checkAnswerCounter.push(0);
        alert(checkAnswerCounter);
        $('.question-container').append(
                    `<div class="control-group">
                      <div class="form-group floating-label-form-group controls">
                        <label>Question-`+ questionCounter + `</label>
                        <input name='check-question-` + checkQuestionCounter + `' type="text" class="form-control" placeholder="Add Check Question" id="check-question-` + checkQuestionCounter + `" required data-validation-required-message="Please enter your Question." >
                      </div>
                    </div>
                    <div class="check-answer-container-` + checkQuestionCounter + `">
                        
                    </div>
        <br>
                    <button type='button' class='check-answer-button btn btn-warning' id='check-answer-button-` + checkQuestionCounter +`'>Add Answer</button>
                    
           <br> `
                );
        
        $('#script-div').remove();
        $('body').append(`<div id="script-div">
            <script src="js/sub-dynamic-form.js" type="text/javascript"></script>
        </div>`);
                
    }
    
    
});