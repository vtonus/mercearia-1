function mensagem(cor,msg){
	$('#respostafeed').html(msg);
	$('#respostafeed').fadeIn();
	$('#respostafeed').css({
	color:cor,	
		
		
	});
	$('#respostafeed').animate({
		 opacity: 1,
		 top:10,
	},500);
	

	$('#respostafeed').delay(1500).animate({
		 opacity: 0,
		 top:-60
	});
	$('#respostafeed').delay(2000).fadeOut();
}



/*---fim de compra---*/







/*---FIM  procura compra---*/
/*tela Novo Fornecimento

var controleLinha=0;

function adcProdutoForn(){
	
	
//	var dados=$('.tabelaForn').html();
	
	var dados="<tr class=linha"+controleLinha+" >"+
	  "  <td><input type='text' size ='16' class='ccodigo' name ='codigo'></td>"+
	   " <td><input type='text' size='40' class='cproduto'></td>"+
	   " <td><input type='text' size ='2' name ='unid1' class='cunid'></td>"+
	  "  <td><input type='text' size ='2' name='qtd1' class='cqtd'></td>"+
	  "  <td><input type='text' size ='1' readonly class='ctotal'></td>"+
    	"<td><img onclick=rmvProdutoForn("+controleLinha+") src= '../images/exclui.png'></td>"+
    ""+
"	</tr>  ";
	
	controleLinha++;
	$('.tabelaForn').append(dados);
	
}

function rmvProdutoForn(i){
     $(".linha"+i).remove();
     controleLinha--;
}
*/











/*tela Novo Fornecimento*/
/*tela Novo Fornecimento*/


/*tela Novo Fornecedor
function novoFornecedor(){
	$.ajax({
		url:'NovoCliente',
		data:$('#formulario').serialize(),
		type:'POST',		
		sucess:function(){
			$('#cadastro').fadeIn();			
		}
	});
	
}
*/