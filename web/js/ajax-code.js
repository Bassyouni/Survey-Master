/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function(){
    
    
    
    $(".suspend-link").click(function(){
        var id = $(this).attr('id').replace('suspend-survey-', '');
        $.get("SuspendSurvey?Id=" + id, function(){
            if( $('#suspend-survey-' + id).html() === 'Resume'){
                $('#suspend-survey-' + id).html('Suspend');
                $('#suspend-survey-' + id).removeClass('text-success');
                $('#suspend-survey-' + id).addClass('text-warning');
            }
            else{
                $('#suspend-survey-' + id).html('Resume');
                $('#suspend-survey-' + id).removeClass('text-warning');
                $('#suspend-survey-' + id).addClass('text-success');
            }
        });
        
    });
    $(".delete-link").click(function(){
        var id = $(this).attr('id').replace('delete-survey-', '');
        $.get('DeleteSurvey?Id=' + id, function(){
            $('#survey-' + id).fadeOut();
        });
    });
});
