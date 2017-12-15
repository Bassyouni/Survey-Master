/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function() {
    var questionCounter = 0;
    var textQuestionCounter = 0;
    var radioQuestionCounter = 0;
    var checkQuestionCounter = 0;
    
    $('#add-text-button').click(addTextQuestion);
    /*var max_fields      = 10; //maximum input boxes allowed
    var wrapper         = $(".input_fields_wrap"); //Fields wrapper
    var add_button      = $(".add_field_button"); //Add button ID
    
    var x = 1; //initlal text box count
    $(add_button).click(function(e){ //on add input button click
        e.preventDefault();
        if(x < max_fields){ //max input box allowed
            x++; //text box increment
            $(wrapper).append('<div><input type="text" name="mytext[]"/><a href="#" class="remove_field">Remove</a></div>'); //add input box
        }
    });
    
    $(wrapper).on("click",".remove_field", function(e){ //user click on remove text
        e.preventDefault(); $(this).parent('div').remove(); x--;
    })*/
    
    function addTextQuestion(){
        textQuestionCounter++
        questionCounter++;
        $('.question-container').append(`<div class="control-group">
                      <div class="form-group floating-label-form-group controls">
                        <label>Question-`+ questionCounter + `</label>
                        <input name='text-question-` + textQuestionCounter + `' type="text" class="form-control" placeholder="Add survey name" id="text-question-` + textQuestionCounter + `" required data-validation-required-message="Please enter your Question." >
                      </div>
                    </div>`);
    }
    
    function addTextQuestion(){
        textQuestionCounter++
        questionCounter++;
        $('.question-container').append(`<div class="control-group">
                      <div class="form-group floating-label-form-group controls">
                        <label>Question-`+ questionCounter + `</label>
                        <input name='text-question-` + textQuestionCounter + `' type="text" class="form-control" placeholder="Add survey name" id="text-question-` + textQuestionCounter + `" required data-validation-required-message="Please enter your Question." >
                      </div>
                    </div>`);
    }
});