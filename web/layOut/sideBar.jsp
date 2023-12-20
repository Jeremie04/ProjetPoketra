
<header>
    <link rel="stylesheet" href="<%=request.getContextPath()%>css/sidebar.css">
</header>
<style>    
    .pic{
        height:4cm; 
        background-image: url('');
        background-size: cover; 
        background-position: center; 
    }
</style>
<div class="full bg-body">
    <div class="container">
        <div class="row">
            <div class="col">
                <div class="pic mt-3"></div>
                <h5 class="text-center">
                    POKETRA
                </h5>
                <hr>
            
                <ul class="list-group m-0 text-center">
                    <div class="row">
                        <a href="<%=request.getContextPath()%>/MatiereController" class="nav-link"><li class="list-group-item border-0">Ajout Matiere</li></a>
                        <a href="<%=request.getContextPath()%>/MatiereParLookController" class="nav-link"><li class="list-group-item border-0">Matiere par Look</li></a>
                        <a href="<%=request.getContextPath()%>/ModeleController" class="nav-link"><li class="list-group-item border-0">Creation Modele</li></a>
                    </div>
                    <a href="#" class="btn btn-light container-fluid mt-5">
                        Deconnecter
                    </a>
                </ul>
            </div>
        </div>
    
    </div>
    
</div>