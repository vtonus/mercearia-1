<!doctype html>
<html class="no-js">
<head>

<meta charset="UTF-8">
<title>Bar & Mercearia do Robinho</title>
<link rel="stylesheet" type="text/css" href="../css/style.css">
<!--IE Compatibility modes
-- >
<meta http-equiv="X-UA-Compatible" content="IE=edge">

<!--Mobile first-->
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- Bootstrap -->
<link rel="stylesheet" href="assets/lib/bootstrap/css/bootstrap.min.css">

<!-- Font Awesome-->
<link rel="stylesheet"	href="/mercearia/views/assets/lib/font-awesome/css/font-awesome.min.css">
<link href="//maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet">
<!-- Metis core stylesheet -->
<link rel="stylesheet" href="assets/css/main.min.css">

<!-- metisMenu stylesheet -->
<link rel="stylesheet" href="assets/lib/metismenu/metisMenu.min.css">
<link rel="stylesheet" href="assets/lib/metismenu/metisMenu.min.css">
<link rel="stylesheet"
	href="assets/lib/datatables/3/dataTables.bootstrap.css">
<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->

<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->

<!--[if lt IE 9]>
      <script src="assets/lib/html5shiv/html5shiv.js"></script>
        <script src="assets/lib/respond/respond.min.js"></script>
        <![endif]-->

<!--For Development Only. Not required -->
<script>
	less = {
		env : "development",
		relativeUrls : false,
		rootpath : "../assets/"
	};
</script>
<link rel="stylesheet" href="assets/css/style-switcher.css">
<link rel="stylesheet/less" type="text/css"
	href="assets/css/less/theme.less">
<script src="assets/lib/less/less-1.7.5.min.js"></script>

<!--Modernizr 2.8.2-->
<script src="assets/lib/modernizr/modernizr.min.js"></script>
</head>
<body class="  ">
	<div class="bg-dark dk" id="wrap">
		<div id="top">

			<!-- .navbar -->
			<nav class="navbar navbar-inverse navbar-static-top">
				<div class="container-fluid">

					<!-- Brand and toggle get grouped for better mobile display -->
					<header class="navbar-header">
						<button type="button" class="navbar-toggle" data-toggle="collapse"
							data-target=".navbar-ex1-collapse">
							<span class="sr-only">Toggle navigation</span> <span
								class="icon-bar"></span> <span class="icon-bar"></span> <span
								class="icon-bar"></span>
						</button>
						<a href="index.html" class="navbar-brand"> <img
							src="assets/img/logo.png" alt="">
						</a>
					</header>
					<div class="topnav">
						<div class="btn-group">
							<a data-placement="bottom" data-original-title="Fullscreen"
								data-toggle="tooltip" class="btn btn-default btn-sm"
								id="toggleFullScreen"> <i
								class="glyphicon glyphicon-fullscreen"></i>
							</a>
						</div>

						<div class="btn-group">
							<a href="login.html" data-toggle="tooltip"
								data-original-title="Logout" data-placement="bottom"
								class="btn btn-metis-1 btn-sm"> <i class="fa fa-power-off"></i>
							</a>
						</div>

					</div>
					<div class="collapse navbar-collapse navbar-ex1-collapse">

						<!-- .nav -->
						<ul class="nav navbar-nav">
							<li><a href="dashboard.html">Painel de Controle</a></li>

						</ul>
						<!-- /.nav -->
					</div>
				</div>
				<!-- /.container-fluid -->
			</nav>
			<!-- /.navbar -->
			<header class="head">

				<div class="main-bar">
					<h3>
						<i class="fa fa-home"></i>&nbsp; Metis
					</h3>
				</div>
				<!-- /.main-bar -->
			</header>
			<!-- /.head -->
		</div>
		<!-- /#top -->
		<div id="left">
			<div class="media user-media bg-dark dker">
				<div class="user-media-toggleHover">
					<span class="fa fa-user"></span>
				</div>
				<div class="user-wrapper bg-dark">

					<div class="media-body">
						<h5 class="media-heading">Archie</h5>
						<ul class="list-unstyled user-info">
							<li><a href="">Administrator</a></li>
							<li>Last Access : <br> <small> <i
									class="fa fa-calendar"></i>&nbsp;16 Mar 16:32
							</small>
							</li>
						</ul>
					</div>
				</div>
			</div>

			<!-- #menu -->
			<ul id="menu" class="bg-blue dker">
				<li class="nav-header">Menu</li>
				<li class="nav-divider"></li>

				<li class="">
					<!-- cadtrato de cliente --> <a href="javascript:;"> <i
						class="fa fa-building "></i> <span class="link-title">Clientes</span>
						<span class="fa arrow"></span>
				</a>
					<ul>
						<li><a onclick="$('.content').load('NovoCliente.jsp')"> <i
								class="fa fa-angle-right"></i>&nbsp; Novo Cliente
						</a></li>
						<li><a onclick="$('.content').load('ProcuraCliente.jsp')">
								<i class="fa fa-angle-right"></i>&nbsp; Gerencia Cliente
						</a></li>



					</ul>


				</li>




				<li class="">
					<!-- cadtrato de Compras --> <a href="javascript:;"> <i
						class="fa fa-building "></i> <span class="link-title">Compras</span>
						<span class="fa arrow"></span>
				</a>
					<ul>
						<li><a onclick="chamatelaCompra()"> <i
								class="fa fa-angle-right"></i>&nbsp; Nova Compra
						</a></li>
						<li><a onclick="$('.content').load('ProcuraCompra.jsp')">
								<i class="fa fa-angle-right"></i>&nbsp; Gerencia Compra
						</a></li>



					</ul>


				</li>





				<li class="">
					<!-- cadtrato de Produtos --> <a href="javascript:;"> <i
						class="fa fa-building "></i> <span class="link-title">Produtos</span>
						<span class="fa arrow"></span>
				</a>
					<ul>
						<li><a onclick="$('.content').load('NovoProduto.jsp')">
								<i class="fa fa-angle-right"></i>&nbsp; Novo Produto
						</a></li>
						<li><a onclick="$('.content').load('ProcuraProduto.jsp')">
								<i class="fa fa-angle-right"></i>&nbsp; Gerencia Produto
						</a></li>



					</ul>


				</li>





				<li class="">
					<!-- cadtrato de Fornecimento(Pedido) --> <a href="javascript:;">
						<i class="fa fa-building "></i> <span class="link-title">Pedido</span>
						<span class="fa arrow"></span>
				</a>
					<ul>
						<li><a onclick="$('.content').load('NovoFornecimento.jsp')">
								<i class="fa fa-angle-right"></i>&nbsp; Novo Pedido
						</a></li>
						<li><a
							onclick="$('.content').load('ProcuraFornecimento.jsp')"> <i
								class="fa fa-angle-right"></i>&nbsp; Gerencia Pedido
						</a></li>



					</ul>


				</li>




				<li class="">
					<!-- cadtrato de Fornecedor --> <a href="javascript:;"> <i
						class="fa fa-building "></i> <span class="link-title">Fornecedor</span>
						<span class="fa arrow"></span>
				</a>
					<ul>
						<li><a onclick="$('.content').load('NovoFornecedor.jsp')">
								<i class="fa fa-angle-right"></i>&nbsp; Novo Fornecedor
						</a></li>
						<li><a onclick="$('.content').load('ProcuraFornecedor.jsp')">
								<i class="fa fa-angle-right"></i>&nbsp; Gerencia Fornecedor
						</a></li>



					</ul>


				</li>



				<li class="">
					<!-- cadtrato de Fornecedor --> <a href="javascript:;"> <i
						class="fa fa-building "></i> <span class="link-title">Funcionário</span>
						<span class="fa arrow"></span>
				</a>
					<ul>
						<li><a onclick="$('.content').load('NovoFuncionario.jsp')">
								<i class="fa fa-angle-right"></i>&nbsp; Novo Funcionário
						</a></li>
						<li><a onclick="$('.content').load('ProcuraFuncionario.jsp')">
								<i class="fa fa-angle-right"></i>&nbsp; Gerencia Funcionário
						</a></li>



					</ul>


				</li>


				<li class="">
					<!-- cadtrato de Fornecedor --> <a href="javascript:;"> <i
						class="fa fa-building "></i> <span class="link-title">Relátorio</span>
						<span class="fa arrow"></span>
				</a>
					<ul>
						<li><a onclick="$('.content').load('Diario.jsp')">
								<i class="fa fa-angle-right"></i>&nbsp;Diario
						</a></li>



					</ul>
					<ul>
						<li><a onclick="$('.content').load('periodo.jsp')">
								<i class="fa fa-angle-right"></i>&nbsp;Periodo
						</a></li>



					</ul>


				</li>







			</ul>
			<!-- /#menu -->
		</div>
		<!-- /#left -->
		<div id="content">
			<div class="outer">
				<div class="inner bg-light lter">
					<div class="content"></div>
				</div>
			</div>
		</div>
		<!-- /#content -->
		<div id="right" class="bg-light lter">
			<div class="alert alert-danger">
				<button type="button" class="close" data-dismiss="alert">&times;</button>
				<strong>Warning!</strong> Best check yo self, you're not looking too
				good.
			</div>

			<!-- .well well-small -->
			<div class="well well-small dark">
				<ul class="list-unstyled">
					<li>Visitor <span class="inlinesparkline pull-right">1,4,4,7,5,9,10</span>
					</li>
					<li>Online Visitor <span class="dynamicsparkline pull-right">Loading..</span>
					</li>
					<li>Popularity <span class="dynamicbar pull-right">Loading..</span>
					</li>
					<li>New Users <span class="inlinebar pull-right">1,3,4,5,3,5</span>
					</li>
				</ul>
			</div>
			<!-- /.well well-small -->

			<!-- .well well-small -->
			<div class="well well-small dark">
				<button class="btn btn-block">Default</button>
				<button class="btn btn-primary btn-block">Primary</button>
				<button class="btn btn-info btn-block">Info</button>
				<button class="btn btn-success btn-block">Success</button>
				<button class="btn btn-danger btn-block">Danger</button>
				<button class="btn btn-warning btn-block">Warning</button>
				<button class="btn btn-inverse btn-block">Inverse</button>
				<button class="btn btn-metis-1 btn-block">btn-metis-1</button>
				<button class="btn btn-metis-2 btn-block">btn-metis-2</button>
				<button class="btn btn-metis-3 btn-block">btn-metis-3</button>
				<button class="btn btn-metis-4 btn-block">btn-metis-4</button>
				<button class="btn btn-metis-5 btn-block">btn-metis-5</button>
				<button class="btn btn-metis-6 btn-block">btn-metis-6</button>
			</div>
			<!-- /.well well-small -->

			<!-- .well well-small -->
			<div class="well well-small dark">
				<span>Default</span> <span class="pull-right"><small>20%</small>
				</span>
				<div class="progress xs">
					<div class="progress-bar progress-bar-info" style="width: 20%"></div>
				</div>
				<span>Success</span> <span class="pull-right"><small>40%</small>
				</span>
				<div class="progress xs">
					<div class="progress-bar progress-bar-success" style="width: 40%"></div>
				</div>
				<span>warning</span> <span class="pull-right"><small>60%</small>
				</span>
				<div class="progress xs">
					<div class="progress-bar progress-bar-warning" style="width: 60%"></div>
				</div>
				<span>Danger</span> <span class="pull-right"><small>80%</small>
				</span>
				<div class="progress xs">
					<div class="progress-bar progress-bar-danger" style="width: 80%"></div>
				</div>
			</div>
		</div>
		<!-- /#right -->
	</div>
	<!-- /#wrap -->
	<footer class="Footer bg-dark dker">
		<p>2014 &copy; Metis Bootstrap Admin Template</p>
	</footer>
	<!-- /#footer -->

	<!-- #helpModal -->
	<div id="helpModal" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title">Modal title</h4>
				</div>
				<div class="modal-body">
					<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit,
						sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.
						Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris
						nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in
						reprehenderit in voluptate velit esse cillum dolore eu fugiat
						nulla pariatur. Excepteur sint occaecat cupidatat non proident,
						sunt in culpa qui officia deserunt mollit anim id est laborum.</p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->
	<!-- /#helpModal -->

	<!--jQuery 2.1.1 -->
	<script src="assets/lib/jquery/jquery.min.js"></script>

	<!--Bootstrap -->
	<script src="assets/lib/bootstrap/js/bootstrap.min.js"></script>

	<!-- MetisMenu -->
	<script src="assets/lib/metismenu/metisMenu.min.js"></script>

	<!-- Screenfull -->
	<script src="assets/lib/screenfull/screenfull.js"></script>
	<script src="assets/lib/screenfull/screenfull.js"></script>
	<script src="assets/lib/datatables/jquery.dataTables.js"></script>
	<script src="assets/lib/datatables/3/dataTables.bootstrap.js"></script>
	<script src="assets/lib/jquery.tablesorter/jquery.tablesorter.min.js"></script>
	<script
		src="assets/lib/jquery-ui-touch-punch/jquery.ui.touch-punch.min.js"></script>

	<!-- Metis core scripts -->
	<script src="assets/js/core.min.js"></script>

	<!-- Metis demo scripts -->
	<script src="assets/js/app.min.js"></script>
	<script src="assets/js/style-switcher.min.js"></script>
	<!--  colando meu CSS e js-->
	<script type="text/javascript" src="../js/jsapi"></script>
	<script type="text/javascript" src="../js/jquery.js"></script>
	<script type="text/javascript" src="../js/scripts.js"></script>
	<script type="text/javascript" src="../js/handlers.js"></script>
	<script type="text/javascript" src="../js/cliente.js"></script>
	<script type="text/javascript" src="../js/relatorios.js"></script>
	<script type="text/javascript" src="../js/compra.js"></script>
	<script type="text/javascript" src="../js/produto.js"></script>
	<script type="text/javascript" src="../js/fornecedor.js"></script>
	<script type="text/javascript" src="../js/fornecimento.js"></script>
	<script type="text/javascript" src="../js/funcionario.js"></script>
	<script type="text/javascript" src="../js/jquery.transit.min.js"></script>

	<div id="respostafeed"
		style="position: absolute; text-align: center; width: 100%; z-index: 99999;"></div>

</body>
</html>
