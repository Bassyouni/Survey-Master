/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function(){
    $(".suspend-user-link").click(function(){
            var id = $(this).attr('id').replace('suspend-user-', '');
            $.get("SuspendUser?id=" + id, function(){
                if( $('#suspend-user-' + id).html() === 'Resume'){
                    $('#suspend-user-' + id).html('Suspend');
                    $('#suspend-user-' + id).removeClass('text-success');
                    $('#suspend-user-' + id).addClass('text-warning');
                }
                else{
                    $('#suspend-user-' + id).html('Resume');
                    $('#suspend-user-' + id).removeClass('text-warning');
                    $('#suspend-user-' + id).addClass('text-success');
                }
            });

        });
});