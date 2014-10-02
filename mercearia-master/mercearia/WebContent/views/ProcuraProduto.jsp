<span class="titulo"> Procura Produtos</span>	
<div id="procli">
	
		<form id="meuForm">
		<table>
		<tr>
		<td>Código do Produto:</td>
		 <td><input type="text" id="codigo" autofocus></td>
		<td>Nome:</td>
		<td><input type="text" id="nome" ></td>
		<tr>
		<td>Valor Min:</td><td><input type="text" id="valorMin" ></td>
		<td>Valor Max:</td><td><input type="text" id="valorMax" ><br/></td>
		<td>Fabricante:</td> <td><input type="text" id="fabricante" ></td>
		<td>validade:</td><td><input type="date" id="validade" ></td>

		<td><input type="button" value="Buscar" onclick="buscaDadosProduto()">	<br /><br /></td>
		</form>
		</table>
		
		<div id="result" style="display:none;">
		
		</div>		
		<div class="resposta" >
	
		</div>	
		<div id="tabdados">
		
		</div>
		<div class="fundoq">
		</div>
		<div class="question">
	 </div>
</div>	
			
