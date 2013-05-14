%% Login de santa claus %%

usuario(santa,polonorte).
usuario(duende,esclavo).
login(X,Y):-
	usuario(X,Y).

%%%% Niños de prueba %%%%

niño(pepito,5,cr,[honesto,regala],[agresivo],[transformer,carrito],100000).
niño(juanita,7,cr,[cariñosa,regala],[deshonesta,manipuladora,quiebracosas],[barbie,barbie],100000).
niño(cacheton,11,cr,[amable,honesto],[quebracosas,malhablado],[ferrari],100000).

%%% Buscar niños con una acción en específico %%%%

% Busqueda en una lista específica %

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
	niño(Nombre,_,_,ListaBuenas,_,_,_),
	busca_lista(ListaBuenas,Accion),
	Resultado=Nombre.

%% Busqueda de malas acciones %%

busqueda_mala(Accion,Lista):-
	findall(Resultado,escaneo_malas(Accion,Resultado),Lista).

escaneo_malas(Accion,Resultado):-
	niño(Nombre,_,_,_,ListaMalas,_,_),
	busca_lista(ListaMalas,Accion),
	Resultado=Nombre.

%%% Buscar niños por ser buenos o malos %%%


%% Lista de niños buenos %%

lista_buenos(Lista):-
	findall(Niño_bueno,escaneo_niños_buenos(Niño_bueno),Resultado),
	Lista = Resultado.

escaneo_niños_buenos(Lista):-
	niño(Nombre,_,_,ListaBuenas,ListaMalas,_,_),
	longitud(ListaBuenas,0,ContadorBuenas),
	longitud(ListaMalas,0,ContadorMalas),
	ContadorMalas=<ContadorBuenas,
	Lista = Nombre.


%% Lista de niños malos %%

lista_malos(Lista):-
	findall(Niño_malo,escaneo_niños_malos(Niño_malo),Resultado),
	Lista = Resultado.

escaneo_niños_malos(Lista):-
	niño(Nombre,_,_,ListaBuenas,ListaMalas,_,_),
	longitud(ListaBuenas,0,ContadorBuenas),
	longitud(ListaMalas,0,ContadorMalas),
	ContadorMalas>ContadorBuenas,
	Lista = Nombre.

%% Lista total de niños buenos y malos %%

lista_niños(Buenos,Malos):-
	lista_buenos(Buenos),
	lista_malos(Malos),!.

%% Merece o no un juguete algun niño %%

niño_merece(ListaBuenas,ListaMalas):-
	longitud(ListaBuenas,0,ContadorBuenas),
	longitud(ListaMalas,0,ContadorMalas),
	ContadorBuenas>=ContadorMalas.

%%%% Juguetes de prueba %%%%

regalo(transformer,hasbro,30000,2).
regalo(ferrari,lego,150000,11).
regalo(barbie,mattel,25000,3).
regalo(carrito,hotWheels,1000,6).

%% Información de regalos y niños %%

informacion_entregas(Informacion):-
	findall(ResultadoNiño,informacion_regalos(ResultadoNiño),Entrega),
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

%% Información sobre los entregables a los niños %%

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

%% Lista de regalos por niño %%

informacion_regalos(Resultado):-
	niño(Nombre,_,_,_,_,_,_),
	lista_regalos(Nombre,Lista),
	Resultado = Lista.

lista_regalos(Nombre,Lista):-
	niño(Nombre,Edad,_,ListaBuenas,ListaMalas,Regalos,Presupuesto),
	niño_merece(ListaBuenas,ListaMalas),
	Dar = 'Regalos',
	regalos(Regalos,[],Resultado,Edad,Presupuesto,0),
	Lista=[Nombre,Edad,Resultado,Dar],!.

lista_regalos(Nombre,Lista):-
	niño(Nombre,Edad,_,ListaBuenas,ListaMalas,Regalos,Presupuesto),
	niño_merece(ListaMalas,ListaBuenas),
	Dar = 'Medias de golfista',
	regalos(Regalos,[],Resultado,Edad,Presupuesto,0),
	Lista=[Nombre,Edad,Resultado,Dar],!.

%% Lista con todos los posibles regalos %%

total_regalos([barbie,transformer,ferrari,carrito]).

%% Encontrar un regalo en específico dentro de la lista de regalos de un niño %%

regalos_niño(Regalo,Nombre):-
	niño(Nombre,_,_,_,_,Lista,_),
	busca_lista(Lista,Regalo).

juguete(Regalo,Lista):-
	findall(Nombre,regalos_niño(Regalo,Nombre),Resultado),
	Lista=Resultado.

%% Contador de regalos iguales solicitados %%

conjunto_regalos(Resultado):-
	total_regalos(Regalos),
	cuenta_regalos(Regalos,[],Resultado).

cuenta_regalos([],ListaTotal,Resultado):-
	Resultado=ListaTotal,!.

cuenta_regalos([Cabeza|Cola],ListaNueva,Resultado):-
	juguete(Cabeza,ListaRegalo),
	longitud(ListaRegalo,0,Total),
	cuenta_regalos(Cola,[[Cabeza,Total]|ListaNueva],Resultado).

%% Ordena listas en orden descendente %%

ordenar_lista([],[]):-!.

ordenar_lista([Cabeza|Cola],Resultado):-
	ordenar_lista(Cola,Insertado),
	insertar_lista(Cabeza,Insertado,Resultado),!.

insertar_lista(Insertado,[],[Insertado]).

insertar_lista(Insertado,[Cabeza|Cola],[Insertado,Cabeza|Cola]):- Insertado>=Cabeza,!.

insertar_lista(Insertado,[Cabeza|Cola],[Cabeza|Temporal]):-
	insertar_lista(Insertado,Cola,Temporal).

%% Ordenamiento de los regalos %%

regalos_lista(Resultado):-
	conjunto_regalos(Regalos),
	ordena_regalos_lista(Regalos,Resultado).

ordena_regalos_lista([],[]):-!.

ordena_regalos_lista([Cabeza|Cola],Temporal):-
	ordena_regalos_lista(Cola,Resultado),
	inserta_regalos_lista(Cabeza,Resultado,Temporal),!.

inserta_regalos_lista(Insertado,[],[Insertado]).

inserta_regalos_lista([Cabeza1|Insertado],[[Cabeza2|Cabeza]|Cola],[[Cabeza1|Insertado],[Cabeza2|Cabeza]|Cola]):-
	Insertado>=Cabeza,!.

inserta_regalos_lista([Cabeza1|Insertado],[[Cabeza2|Cabeza]|Cola],[[Cabeza2|Cabeza]|Temporal]):-
	inserta_regalos_lista([Cabeza1|Insertado],Cola,Temporal).






















