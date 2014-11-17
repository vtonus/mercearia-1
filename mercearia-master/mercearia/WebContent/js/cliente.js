/*funções do NovoCliente.jsp*/
function novoCliente(){
	$.ajax({
		url:'../NovoCliente',
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
	var pergunta=	"<div class='panel panel-default' style='width: 589px; '>"+
	"<div class='panel-heading'>"+
	"<div class='panel-title'>"+
	"	<i class='glyphicon glyphicon-wrench pull-right'></i>"+
		"<h3 class='panel-title'>Voc&ecirc; realmente qua3453453sdasr Excluir:<br>" + $('#nome'+pk).val()+"?</h3></div></div>"+
				 " <div class='btn btn-primary' onclick='excluiCliente("+pk+")'>Sim</div> <div class='btn btn-danger' onclick='cExcluirCliente("+pk+")'>N&atilde;o</div>";
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
	
	$("#tabdados #nome"+pk).show();
	$("#tabdados #telefone"+pk).show();
	$("#tabdados #doc"+pk).show();
	$("#tabdados #email"+pk).show();
	$("#tabdados #sexo"+pk).show();
	$("#tabdados #data"+pk).show();
	$("#tabdados #edita"+pk).show();
	$("#tabdados #end"+pk).show();
	
	$("#tabdados #salva"+pk).hide();
	
	
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
	
	$("#tabdados #nome"+pk).hide();
	$("#tabdados #telefone"+pk).hide();
	$("#tabdados #doc"+pk).hide();
	$("#tabdados #email"+pk).hide();
	$("#tabdados #sexo"+pk).hide();
	$("#tabdados #data"+pk).hide();
	$("#tabdados #end"+pk).hide();
	$("#tabdados #edita"+pk).hide();
	$("#tabdados  #salva"+pk).show();
	
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
		url:"../BuscaCliente",
		data:{palavraChave : $("#palavraChave").val(),
		parametro : $("#parametro").val()},
		type: 'POST',
		success: function(data){
			$('#result').html(data);
			var i=0;
		var	dados="<div id='collapse4' class='body'><table id='dataTable' class='table table-bordered table-condensed table-hover table-striped' >" +
					"<thead><tr><th>Nome</th>" +
					"<th>Telefone</th>" +
					"<th>CPF</th>" +
					"<th>Email</th>" +
					"<th>Endereco</th>" +
					"<th>Sexo</th>" +
					"<th>Data de Nasc.</th>" +
					"<th></th>" +
					"<th></th></tr> " +
					" </thead><tbody>"
				;
		
	     
          
       
			while($("#nome"+i).val()!=null){
			if(i%2!=0){
				 impar="";//class='impar'
				
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
				"<td><span id='nome"+i+"'>" + $("#nome"+i).val()+ "</span><input style='width:100%'  type='text' class='nome"+i+"' value='"+$("#nome"+i).val()+"'></input></td>" +
		 		"<td><span id='telefone"+i+"'>" + $("#telefone"+i).val()+ " </span><input style='width:100%' type='text' class='telefone"+i+"' value='"+$("#telefone"+i).val()+"'></input></td>" +
		 		"<td><span id='doc"+i+"'>" + $("#cpf"+i).val()+ "</span><input  type='text' class='doc"+i+"' value='"+$("#cpf"+i).val()+"'  style='width:100%' ></input></td>" +
		 		"<td><span id='email"+i+"'>" + $("#email"+i).val()+ "</span><input  type='text' class='email"+i+"' value='"+$("#email"+i).val()+"'></input></td>" +
		 		
		 		"<td><span id='end"+i+"'>" + $("#endereco"+i).val()+ "</span><input  type='text' class='end"+i+"' value='"+$("#endereco"+i).val()+"'></input></td>" +
		 		
		 		
		 		"<td><span id='sexo"+i+"' >" + $("#sexo"+i).val()+ " </span><label class='estiloso sexo"+i+"' ><select  name='sexo' class='sexo"+i+" sexo1"+i+"' value='"+$("#sexo"+i).val()+"'><option value='masculino' "+selecionam+">Masculino</option><option value='feminino' "+seleciona+">Feminino </option></select></label> </td>" +
		 		"<td><span id='data"+i+"'>" + $("#dataNascimento"+i).val()+ "</span><input  type='date' class='data"+i+"' value='"+$("#dataNascimento"+i).val()+"'></input></td>" +//+$("#dataNascimento"+i).val()+
		 		
		 		"<td><span  class='clickable glyphicon glyphicon-pencil' id='edita"+i+"' onclick='mostraedita("+i+")' src='../images/edita.png' alt='editar' />   <span  alt='salvar' id='salva"+i+"' onclick='pEditaCliente("+i+")' style='display:none'  class='clickable glyphicon glyphicon-save' /><span class='clickable glyphicon glyphicon-floppy-remove' id='salva"+i+"' onclick='cEditaCliente("+i+")' style='display:none' /></td>"+
		 		
		 		"<td><span class='clickable glyphicon glyphicon-remove' onclick='pExcluiCliente("+i+")' alt='excluir'/></span></td>"+"</tr>";
		 		 i++;
				
		}		
			dados+=" </tbody></table></div> " +
					" <script>"+
    " $(b()); function b() {" +
     "   Metis.MetisTable();"+
     "   Metis.metisSortable();"+
    "  }"+
   " </script>";
			
		
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

