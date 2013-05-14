%% Login de santa claus %%

usuario(santa,polonorte).
usuario(duende,esclavo).
login(X,Y):-
	usuario(X,Y).

%%%% Ni�os de prueba %%%%

ni�o(pepito,5,cr,[honesto,regala],[agresivo],[transformer,carrito],100000).
ni�o(juanita,7,cr,[cari�osa,regala],[deshonesta,manipuladora,quiebracosas],[barbie,barbie],100000).
ni�o(cacheton,11,cr,[amable,honesto],[quebracosas,malhablado],[ferrari],100000).

%%% Buscar ni�os con una acci�n en espec�fico %%%%

% Busqueda en una lista espec�fica %
busca_lista([],Elemento):-fail.

busca_lista([Cabeza|Cola],Elemento):-
	Cabeza=Elemento,!.

busca_lista([Cabeza|Cola],Elemento):-
	busca_lista(Cola,Elemento).

% Longitud de la lista %

longitud([],Resultado,Cuenta):-
	Resultado is Cuenta.

longitud([Cabeza|Cola],Resultado,Cuenta):-
	Contador is Cuenta+1,
	longitud(Cola,Contador,Resultado).


%% Busqueda de buenas acciones %%

busqueda_buena(Accion,Lista):-
	findall(Resultado,escaneo_buenas(Accion,Resultado),Lista).

escaneo_buenas(Accion,Resultado):-
	ni�o(Nombre,_,_,ListaBuenas,_,_,_),
	busca_lista(ListaBuenas,Accion),
	Resultado=Nombre.

%% Busqueda de malas acciones %%

busqueda_mala(Accion,Lista):-
	findall(Resultado,escaneo_malas(Accion,Resultado),Lista).

escaneo_malas(Accion,Resultado):-
	ni�o(Nombre,_,_,_,ListaMalas,_,_),
	busca_lista(ListaMalas,Accion),
	Resultado=Nombre.

%%% Buscar ni�os por ser buenos o malos %%%


%% Lista de ni�os buenos %%

lista_buenos(Lista):-
	findall(Ni�o_bueno,escaneo_ni�os_buenos(Ni�o_bueno),Resultado),
	Lista = Resultado.

escaneo_ni�os_buenos(Lista):-
	ni�o(Nombre,_,_,ListaBuenas,ListaMalas,_,_),
	longitud(ListaBuenas,0,ContadorBuenas),
	longitud(ListaMalas,0,ContadorMalas),
	ContadorMalas=<ContadorBuenas,
	Lista = Nombre.


%% Lista de ni�os malos %%

lista_malos(Lista):-
	findall(Ni�o_malo,escaneo_ni�os_malos(Ni�o_malo),Resultado),
	Lista = Resultado.

escaneo_ni�os_malos(Lista):-
	ni�o(Nombre,_,_,ListaBuenas,ListaMalas,_,_),
	longitud(ListaBuenas,0,ContadorBuenas),
	longitud(ListaMalas,0,ContadorMalas),
	ContadorMalas>ContadorBuenas,
	Lista = Nombre.

%% Lista total de ni�os buenos y malos %%

lista_ni�os(Buenos,Malos):-
	lista_buenos(Buenos),
	lista_malos(Malos),!.

%% Merece o no un juguete algun ni�o %%

ni�o_merece(ListaBuenas,ListaMalas):-
	longitud(ListaBuenas,0,ContadorBuenas),
	longitud(ListaMalas,0,ContadorMalas),
	ContadorBuenas>=ContadorMalas.

%%%% Juguetes de prueba %%%%

regalo(transformer,hasbro,30000,2).
regalo(ferrari,lego,150000,11).
regalo(barbie,mattel,25000,3).
regalo(carrito,hotWheels,1000,6).

%% Informaci�n de regalos y ni�os %%

informacion_entregas(Informacion):-
	findall(ResultadoNi�o,informacion_regalos(ResultadoNi�o),Entrega),
	Informacion = Entrega.

%% Atributos de los regalos %%

edad_regalo(Nombre,Edad):-
	regalo(Nombre,_,_,Edad_Minima),
	Edad = Edad_Minima.

precio_regalo(Nombre,Presupuesto):-
	regalo(Nombre,_,Costo,_),
	Presupuesto = Costo.

informacion_juguete(Regalo,Lista):-
	regalo(Regalo,Marca,Costo,Edad),
	Lista=[Regalo,Marca,Costo,Edad].

%% Informaci�n sobre los entregables a los ni�os %%

regalos([],NuevaLista,Resultado,_,_,_):-
	Resultado = NuevaLista,!.

regalos([Cabeza|Cola],NuevaLista,Resultado,Edad,Presupuesto,Cuenta):-
	edad_regalo(Cabeza,Edad_Minima),
	Edad>=Edad_Minima,
	precio_regalo(Cabeza,Costo),
	Acumulado is Cuenta+Costo,
	Presupuesto>=Acumulado,
	informacion_juguete(Cabeza,ListaRegalo),
	regalos(Cola,[ListaRegalo|NuevaLista],Resultado,Edad,Presupuesto,Acumulado),!.

regalos([Cabeza|Cola],NuevaLista,Resultado,Edad,Presupuesto,Cuenta):-
	regalos(Cola,NuevaLista,Resultado,Edad,Presupuesto,Cuenta),!.

%% Lista de regalos por ni�o %%

informacion_regalos(Resultado):-
	ni�o(Nombre,_,_,_,_,_,_),
	lista_regalos(Nombre,Lista),
	Resultado = Lista.

lista_regalos(Nombre,Lista):-
	ni�o(Nombre,Edad,_,ListaBuenas,ListaMalas,Regalos,Presupuesto),
	ni�o_merece(ListaBuenas,ListaMalas),
	Dar = 'Regalos',
	regalos(Regalos,[],Resultado,Edad,Presupuesto,0),
	Lista=[Nombre,Edad,Resultado,Dar],!.

lista_regalos(Nombre,Lista):-
	ni�o(Nombre,Edad,_,ListaBuenas,ListaMalas,Regalos,Presupuesto),
	ni�o_merece(ListaMalas,ListaBuenas),
	Dar = 'Medias de golfista',
	regalos(Regalos,[],Resultado,Edad,Presupuesto,0),
	Lista=[Nombre,Edad,Resultado,Dar],!.

%% Lista con todos los posibles regalos %%

total_regalos([barbie,transformer,ferrari,carrito]).

%% Encuentra un regalo en espec�fico dentro de la lista de regalos de un ni�o %%

regalos_ni�o(Regalo,Nombre):-
	ni�o(Nombre,_,_,_,_,Lista,_),
	busca_lista(Lista,Regalo).

juguete(Regalo,Lista):-
	findall(Nombre,regalos_ni�o(Regalo,Nombre),Resultado),
	Lista=Resultado.

/*Cuenta la cantidad de juguetes y hace una lista de estos*/
listaRecord(Resultado):-
	total_regalos(Regalo),
	cuenta_regalos(Regalo,[],Resultado).

cuenta_regalos([],ListaTotal,Resultado):-
	Resultado=ListaTotal,!.

cuenta_regalos([Cabeza|Cola],ListaNueva,Resultado):-
	juguete(Cabeza,ListaRegalo),
	longitud(ListaRegalo,0,Total),
	cuenta_regalos(Cola,[[Cabeza,Total]|ListaNueva],Resultado).









