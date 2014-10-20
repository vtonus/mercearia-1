/*funções do NovoCliente.jsp*/
function novoCliente(){
	$.ajax({
		url:'NovoCliente',
		data:$('#formulario').serialize(),
		type:'POST',		
		success:function(){
			$('#cadastro').fadeIn();		
			mensagem("green","O Cliente foi cadastrado!!");
			$('.cad input').val('');
		},
		error:function(){
			$('#cadastro').fadeIn();		
			mensagem("red","Ocorreu um erro, o Cliente não foi adicionado!!");
		}
	});
	
}
function pExcluiCliente(pk){
	var pergunta=	"<span style='width: 589px;    font-size: 16px;    font-weight: bold;'"+
	">Voc&ecirc; realmente quer Excluir:<br>" + $('#nome'+pk).val()+"?</span>"+
				 " <div class='yes' onclick='excluiCliente("+pk+")'>Sim</div> <div class='nope' onclick='cExcluirCliente("+pk+")'>N&atilde;o</div>";
		$('#procli .fundoq').show();
		$("#procli .question").show();
		$("#procli .question").html(pergunta);

}
	
function cExcluirCliente(pk){
	$('#procli .fundoq').hide();
	$("#procli .question").hide();
}

function excluiCliente(pk){
	$.ajax({
		url:'ExcluiCliente',
		data: { id: $("#id"+pk).val()},
		type:'POST',	
		success: function(back){
			
	
			cEditaCliente(pk);
			mensagem("green", "Cliente Excluido");
			buscaDadosCliente();
			
		},
		error:function(){
			cEditaCliente(pk);
			buscaDadosCliente();
			/*$("#procli .resposta").fadeIn();
			setTimeout(function(){$("#procli .resposta").fadeOut();},3000);
			$("#procli .resposta").css({
				color:'red',
				borderColor:"red",
					
				});
			$("#procli .resposta").html("Ocorreu um erro, o Cliente não foi excluido!!");*/
	
			}
	});
}
/*Fim da funções do NovoCliente.jsp*/
/* Começa o cliente exeistente*/

function pEditaCliente(pk){
var pergunta=	"<span style='width: 589px;    font-size: 16px;    font-weight: bold;'"+
">Voc&ecirc; realmente quer editar:<br>" + $('#nome'+pk).val()+"?</span>"+
			 " <div class='yes' onclick='editaCliente("+pk+")'>Sim</div> <div class='nope' onclick='cEditaCliente("+pk+")'>N&atilde;o</div>";
	$('#procli .fundoq').show();
	$("#procli .question").show();
	$("#procli .question").html(pergunta);
}
	
function editaCliente(pk){
	
	
	$.ajax({
		url:'EditaCliente',
		data:{
		id:$("#id"+pk).val(),nome:$(".nome"+pk).val(),cpf:$(".doc"+pk).val(),telefone:$(".telefone"+pk).val(),email:$(".email"+pk).val(),sexo:$(".sexo1"+pk).val(),dataDeNascimento:$(".data"+pk).val(),
		end:$(".end"+pk).val(),
		},
		type: 'POST',
		success: function(back){
			cEditaCliente(pk);
			buscaDadosCliente();
			mensagem("green", "Cliente alterado");
		},
		error:function(){
			cEditaCliente(pk);
			buscaDadosCliente();
			mensagem("green", "Cliente alterado");}
	});
	
}

function cEditaCliente(pk){
	$(".nome"+pk).hide();
	$(".telefone"+pk).hide();
	$(".doc"+pk).hide();
	$(".email"+pk).hide();
	$(".sexo"+pk).hide();
	$(".data"+pk).hide();
	$(".end"+pk).hide();
	
	$(".tabretorno #nome"+pk).show();
	$(".tabretorno #telefone"+pk).show();
	$(".tabretorno #doc"+pk).show();
	$(".tabretorno #email"+pk).show();
	$(".tabretorno #sexo"+pk).show();
	$(".tabretorno #data"+pk).show();
	$(".tabretorno #edita"+pk).show();
	$(".tabretorno #end"+pk).show();
	
	$(".tabretorno #salva"+pk).hide();
	
	
	$('#procli .fundoq').hide();
	$("#procli .question").hide();
	
}

function mostraedita(pk){

	$(".nome"+pk).show();
	$(".telefone"+pk).show();
	$(".doc"+pk).show();
	$(".email"+pk).show();
	$(".sexo"+pk).show();
	$(".data"+pk).show();
	$(".end"+pk).show();
	
	$(".tabretorno #nome"+pk).hide();
	$(".tabretorno #telefone"+pk).hide();
	$(".tabretorno #doc"+pk).hide();
	$(".tabretorno #email"+pk).hide();
	$(".tabretorno #sexo"+pk).hide();
	$(".tabretorno #data"+pk).hide();
	$(".tabretorno #end"+pk).hide();
	$(".tabretorno #edita"+pk).hide();
	$(".tabretorno #salva"+pk).show();
	
}




function validateForm() {
	var x = document.forms["myForm"]["nome"].value;
	if (x == null || x == "") {
		alert("Campo nome precisa ser preenchido.");
		return false;
	}
}

function buscaDadosCliente() {
	var impar="class='impar'";
	$('#result').html('');
	$('#tabdados').html('');
	$.ajax({
		url:"BuscaCliente",
		data:{palavraChave : $("#palavraChave").val(),
		parametro : $("#parametro").val()},
		type: 'POST',
		success: function(data){
			$('#result').html(data);
			var i=0;
		var	dados="<table class='tabretorno' cellspacing='0' cellspadding='0'>" +
					"<tr><th>Nome</th>" +
					"<th>Telefone</th>" +
					"<th>CPF</th>" +
					"<th>Email</th>" +
					"<th>Endereco</th>" +
					"<th>Sexo</th>" +
					"<th>Data de Nasc.</th>" +
					"<th></th>" +
					"<th></th></tr>"
				;
			while($("#nome"+i).val()!=null){
			if(i%2!=0){
				 impar="class='impar'";
				
			}else{
				 impar="";
			}
			 if($("#sexo"+i).val()=='feminino'){ 
				 seleciona="selected";
				 selecionam="";				 
			 }else
				 seleciona="";
			    selecionam="selected";	
			 
				dados+="" +
						"<tr "+impar+"> " +
				"<td><span id='nome"+i+"'>" + $("#nome"+i).val()+ "</span><input  type='text' class='nome"+i+"' value='"+$("#nome"+i).val()+"'></input></td>" +
		 		"<td><span id='telefone"+i+"'>" + $("#telefone"+i).val()+ " </span><input style='width:90px' type='text' class='telefone"+i+"' value='"+$("#telefone"+i).val()+"'></input></td>" +
		 		"<td><span id='doc"+i+"'>" + $("#cpf"+i).val()+ "</span><input  type='text' class='doc"+i+"' value='"+$("#cpf"+i).val()+"'  style='width:104px'></input></td>" +
		 		"<td><span id='email"+i+"'>" + $("#email"+i).val()+ "</span><input  type='text' class='email"+i+"' value='"+$("#email"+i).val()+"'></input></td>" +
		 		
		 		"<td><span id='end"+i+"'>" + $("#endereco"+i).val()+ "</span><input  type='text' class='end"+i+"' value='"+$("#endereco"+i).val()+"'></input></td>" +
		 		
		 		
		 		"<td><span id='sexo"+i+"' >" + $("#sexo"+i).val()+ " </span><label class='estiloso sexo"+i+"' ><select  name='sexo' class='sexo"+i+" sexo1"+i+"' value='"+$("#sexo"+i).val()+"'><option value='masculino' "+selecionam+">Masculino</option><option value='feminino' "+seleciona+">Feminino </option></select></label> </td>" +
		 		"<td><span id='data"+i+"'>" + $("#dataNascimento"+i).val()+ "</span><input  type='date' class='data"+i+"' value='"+$("#dataNascimento"+i).val()+"'></input></td>" +//+$("#dataNascimento"+i).val()+
		 		
		 		"<td><img  class='clickable' id='edita"+i+"' onclick='mostraedita("+i+")' src='../images/edita.png' alt='editar' />   <img alt='salvar' id='salva"+i+"' onclick='pEditaCliente("+i+")' style='display:none'  class='clickable' src='../images/salva.png' /><img class='clickable' id='salva"+i+"' onclick='cEditaCliente("+i+")' style='display:none' src='../images/close15.png' /></td>"+
		 		
		 		"<td><img class='clickable' onclick='pExcluiCliente("+i+")' src='../images/exclui.png' alt='excluir'/></td>"+"</tr>";
		 		 i++;
				
		}		
			dados+="</table>";
			
		
			$('#tabdados').html(dados);
		}
	});
}

function excluiDados() {

}

function editaDados() {

}

function carregaCliente(i){
	$("#nome").val($("#nome"+i).val());
	if ($("#doc"+i).val() > 9){
	$("#doc").val("cnpj");
	}
	else {
	$("#doc").val("cpf");
	}
	$("#docNumero").val($("#doc"+i).val());
	$("#telefone").val($("#telefone"+i).val());
	$("#email").val($("#email"+i).val());
	$("#dataNascimento").val($("#data"+i).val());
}

function deslogar()
{
	document.location = "/Deslogando";
}

