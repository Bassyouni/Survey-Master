/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/* global radioAnswerCounter, checkAnswerCounter */

$(document).ready(function() {
    /*name is 'radio-question-1-answer-2'*/
    $('.radio-answer-button').unbind().click(function (){
        var id = $(this).attr('id').replace('radio-answer-button-', '');
        radioAnswerCounter[parseInt(id-1)]++;
        $('.radio-answer-container-' + id).append(`<div class="control-group">
                      <div class="form-group floating-label-form-group controls">
                        <label>Answer-`+ radioAnswerCounter[parseInt(id-1)] + `</label>
                        <input name='radio-question-` + id + '-answer-' + radioAnswerCounter[parseInt(id-1)] + `' type="text" class="form-control" placeholder="Add Radio Answer here..." id="radio-question-` + id + `-answer-` + radioAnswerCounter[parseInt(id-1)] + `" required data-validation-required-message="Please enter your Question." >
                      </div>
                    </div>`);
    });
    
    /*name is 'check-question-1-answer-2'*/
    $('.check-answer-button').unbind().click(function (){
        var id = $(this).attr('id').replace('check-answer-button-', '');
        checkAnswerCounter[parseInt(id-1)]++;
        $('.check-answer-container-' + id).append(`<div class="control-group">
                      <div class="form-group floating-label-form-group controls">
                        <label>Answer-`+ checkAnswerCounter[parseInt(id-1)] + `</label>
                        <input name='check-question-` + id + '-answer-' + checkAnswerCounter[parseInt(id-1)] + `' type="text" class="form-control" placeholder="Add Radio Answer here..." id="radio-question-` + id + `-answer-` + checkAnswerCounter[parseInt(id-1)] + `" required data-validation-required-message="Please enter your Question." >
                      </div>
                    </div>`);
    });
});
