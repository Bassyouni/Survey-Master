/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function(){
    
    $('#confirmation').keyup(function(){
        var confirmation = $('#confirmation').val();
        var password = $('#new-password').val();
        if(confirmation === password){
            $('#new-password').css('color', '#3CE02D');
            $('#confirmation').css('color', '#3CE02D');
            $('#sendMessageButton').removeAttr("disabled");
        }
        else{
            $('#new-password').css('color', 'red');
            $('#confirmation').css('color', 'red');
            $('#sendMessageButton').attr("disabled", true);
        }
    });
    $('#new-password').keyup(function(){
        var confirmation = $('#confirmation').val();
        var password = $('#new-password').val();
        if(confirmation === password){
            $('#new-password').css('color', '#3CE02D');
            $('#confirmation').css('color', '#3CE02D');
            $('#sendMessageButton').removeAttr("disabled");
        }
        else{
            $('#new-password').css('color', 'red');
            $('#confirmation').css('color', 'red');
            $('#sendMessageButton').attr("disabled", true);
        }
    });
    
    
});
