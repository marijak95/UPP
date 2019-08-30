$(document).ready(function () {
	var url_string = window.location.href;
	var url = new URL(url_string);
	var taskId = url.searchParams.get("taskId");
	var naucneOblasti;
	
	$.ajax({
		async: false,
		url: "http://localhost:3000/korisnik/getRecenzenti/"+taskId,
        type: "GET",
        dataType: "json",
        success: function (data) {
        	if(data!=null) {
        		var tabelaRecenzenti = $('#tabelaRecenzenti');
        		var divRecenzenti = $('#divRecenzenti');
        		for(i=0;i<data.length;i++){
        			tabelaRecenzenti.append('<tr><td>'+data[i].ime+'</td><td>'+data[i].prezime+'</td><td>'+data[i].email+'</td><td>'+data[i].grad+'</td><td>'+data[i].drzava+'</td><td><input name=\"check\" type=\"checkbox\" id=\"checkboxOdaberiRecenzenta'+data[i].id+'\" value=\"'+data[i].id+'\"/></td></tr>');
        			divRecenzenti.append(tabelaRecenzenti);
    	        	}
        		divRecenzenti.append("<button style=\"margin-left:15%\" class=\"button-home\" onclick=\"odaberiRecenzente()\">Odaberi</button></td>");
        	} else {
        		top.location.href = "home.html";
        	}
        },
        error: function (jqxhr, textStatus, errorThrown) {
        	top.location.href = "home.html";                
        }
    });
})

function odaberiRecenzente(){
	var nizRecenzenata = [];
	$("input:checkbox[name=check]:checked").each(function(){
		var thiss = $(this);
		var value = $(this).val();
		nizRecenzenata.push($(this).val());
	});
	var vreme = "10D";
	
	var url_string = window.location.href;
	var url = new URL(url_string);
	var taskId = url.searchParams.get("taskId");
	
	var recenziranjeDTO = JSON.stringify({
		"idRecenzenata": nizRecenzenata,
		"vremeRecenziranja": vreme
	});
	
	$.ajax({
		async: false,
		url: "http://localhost:3000/korisnik/submitRecenzenti/"+taskId,
        type: "POST",
        contentType: "application/json",
        data:recenziranjeDTO,
        success: function () {
        	toastr["success"]("recenzenti dodeljeni.");
        },
        error: function (jqxhr, textStatus, errorThrown) {
        	top.location.href = "home.html";                
        }
    });
	
}