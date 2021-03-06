var IDS_TEXT_WIDTH = 9.2; // Ancho del texto del interior de las listas
var IDS_TEXT_HEIGHT = 14; // Alto del texto del interior de las listas

// Borra un elemento de un Array y reordena su longitud
function DeleteArray(oArray, iIndex)
{
   for(var ii=iIndex; ii < (oArray.length-1); ii++)
   {
      delete oArray[ii];
      oArray[ii] = oArray[ii + 1];
   }
   delete oArray[ii];
   oArray.length--;

   return oArray;
}

// Constructor del array de eventos
function ArrOfEvents(strEventName, strEventValue)
{
   this.EventName = strEventName;
   this.EventValue = strEventValue;
}

// campos del array de listas (puntero a List, y a Div)
function FldsList(oDiv, oList)
{
   this.oList = oList;
   this.oDiv = oDiv;
}

///////////////////////////////////////////
// OBJETO LISTVIEW
///////////////////////////////////////////
// Es el constructor del objeto
function ListView(Id, strObjName, strFormName)
{
   this.Id = Id;
   this.oListView = new Array();
   this.oRows = new Array();
   this.numRows = 0;
   this.oForm = eval("document.getElementById('" + strFormName + "')"); // Formulario donde esta la lista
   this.ObjName = strObjName;
   // Definimos los metodos del objeto
   this.CreateHeadList = CreateHeadList;
   this.CreateListDe = CreateListDe;
   this.CreateListIz = CreateListIz;
   this.DestructListView = DestructListView; // Destructor
   this.DelRowById = DelRowById;
   this.DelRowByPos = DelRowByPos;
   this.DelAllRowsSelected = DelAllRowsSelected;
   this.AddRow = AddRow;
   this.getListElems = getListElems;
   this.getListSelectedIndex = getListSelectedIndex;
   this.getNumRowsSelected = getNumRowsSelected;
   this.UpRowSelected = UpRowSelected;
   this.DownRowSelected = DownRowSelected;
   this.SelectInOtherList = SelectInOtherList;
   this.OnMoveScrollLeft = OnMoveScrollLeft;
   this.UnSelectAllElems = UnSelectAllElems;
}

// Funcion que genera el codigo de una lista de las de la izquierda (sin scrollvertical)
function CreateListIz(strDivWidth, strDivHeight, strDivTop, strDivLeft,
      strListHeight, strListWidth, strBorderColor, oArrEventsList)
{
	var strOnChangeList = this.ObjName + ".SelectInOtherList(this);";
	var strIdDiv = "DIV" + this.oListView.length.toString();
	var oDiv = document.createElement("div");
	var strIdList = "LIST" + this.oListView.length.toString();
    var oList = document.createElement("select");

	oDiv.setAttribute("id", strIdDiv);

	oDiv.style.position = "absolute";
	oDiv.style.top = strDivTop;
	oDiv.style.left = strDivLeft;
	oDiv.style.height = strDivHeight;
	oDiv.style.width = strDivWidth;
	oDiv.style.overflowX = "hidden";
	oDiv.style.overflowY = "hidden";
    oDiv.style.margin=0;
    oDiv.style.padding=0;
	//oDiv.style.border= '2px solid #' + strBorderColor;
	oDiv.style.border= '2px solid #0eaae8';

	this.oForm.appendChild(oDiv);

    oList.setAttribute("id", strIdList);
    oList.setAttribute("className", "combo");
    oList.setAttribute("name", strIdList);
	oList.setAttribute("min_Height", strListHeight );
	oList.setAttribute("min_Width", strListWidth );

	oList.style.position = "absolute";
	oList.style.top = -2;
	oList.style.left = -2;
	oList.style.width = strListWidth;
	oList.style.height = strListHeight;      
	oList.style.overflowY = "hidden";	
	
	if (oArrEventsList != null){
		for(var ii = 0; ii < oArrEventsList.length; ii++){
			if ( (oArrEventsList[ii].EventName == "onchange") || (oArrEventsList[ii].EventName == "ONCHANGE") ){
				strOnChangeList += oArrEventsList[ii].EventValue;
			}
			else {
				var fnEvent = oArrEventsList[ii].EventValue.toString();
			
				oList[oArrEventsList[ii].EventName.toString()] = function(){eval(fnEvent)};
			}
		} 
	}
	
	oList["onchange"] = function(){eval(strOnChangeList)};
	window.setTimeout("SetMultiple('" + strIdList + "');", 1);
	
	oDiv.innerHTML = "";
	oDiv.appendChild(oList);
	
	this.oListView[this.oListView.length] = new FldsList(oDiv, oList);

	return;
}

function SetMultiple(idList)
{
	var oList = document.getElementById(idList);
	
	if (oList){
		oList.setAttribute("multiple", true);
	}
}


// Funcion que genera el codigo de la lista de la derecha (con scrollvertical)
function CreateListDe(strDivWidth, strDivHeight, strDivTop, strDivLeft,
      strListHeight, strListWidth, strBorderColor, oArrEventsList)
{
	var strOnChangeList = this.ObjName + ".SelectInOtherList(this);";
	var fnOnMoveScroll = this.ObjName + ".OnMoveScrollLeft(top.GetEventPositionX(event));";
	var strIdDiv = "DIV" + this.oListView.length.toString();
	var strIdList = "LIST" + this.oListView.length.toString();
	var oDiv = document.createElement("div");
	var oList = document.createElement("select");
	
	oDiv.setAttribute("id", strIdDiv);

	oDiv.style.position = "absolute";
	oDiv.style.top = strDivTop;
	oDiv.style.left = strDivLeft;
	oDiv.style.height = strDivHeight;
	oDiv.style.width = strDivWidth;
 	oDiv.style.overflowX = "hidden";
	oDiv.style.overflowY = "auto";
	oDiv.style.margin=0;
	oDiv.style.padding=0;
	//oDiv.style.border = '2px solid #' + strBorderColor;
	oDiv.style.border = '2px solid #0eaae8';
	
	oDiv["onscroll"] = function(){eval(fnOnMoveScroll)};
   
	this.oForm.appendChild(oDiv);
      
	oList.setAttribute("className", "combo");
	oList.setAttribute("id", strIdList);
    oList.setAttribute("name", strIdList);
	oList.setAttribute("min_Height", strListHeight );
	oList.setAttribute("min_Width", strListWidth );
	
	oList.style.position = "absolute";
	oList.style.top = -3;
	oList.style.left = -3;
	oList.style.width = strListWidth;
	oList.style.height = strListHeight;      
	oList.style.overflowY = "hidden";	
         
	if (oArrEventsList != null) {
		for(var ii = 0; ii < oArrEventsList.length; ii++){
			if ( (oArrEventsList[ii].EventName == "onchange") || (oArrEventsList[ii].EventName == "ONCHANGE") ) {
				strOnChangeList += oArrEventsList[ii].EventValue;
			}
			else {
				var fnEvent = oArrEventsList[ii].EventValue.toString();
				
				oList[oArrEventsList[ii].EventName] = function(){eval(fnEvent)};
			}
		} 
	}
   
	oList["onchange"] = function(){eval(strOnChangeList)};
	window.setTimeout("SetMultiple('" + strIdList + "');", 1);

	oDiv.innerHTML = "";
	oDiv.appendChild(oList);
	
	this.oListView[this.oListView.length] = new FldsList(oDiv, oList);
    
	return;
}


// Funcion que crea la cabecera de la lista
function CreateHeadList(strHeadTop, strHeadLeft, strHeadWidth,
   strHeadHeight, str_IDS_Head, strBackgrColor, strBorderColor)
{
   var strHeadId = "DIVHEAD" + this.oListView.length.toString();
   var oDiv = document.createElement("div");
   var p = document.createElement("p");
   var headerText = document.createTextNode(str_IDS_Head);
      
   oDiv.setAttribute("id", strHeadId);
   
   oDiv.style.position = "absolute";
   oDiv.style.top = strHeadTop;
   oDiv.style.left = strHeadLeft;
   oDiv.style.width = strHeadWidth;
   oDiv.style.height = strHeadHeight;
   //oDiv.style.backgroundColor = strBackgrColor;
   oDiv.style.backgroundColor = "0eaae8";
   oDiv.style.padding=0;
   //oDiv.style.border = '2px solid #' + strBackgrColor;
	 oDiv.style.border= '2px solid #0eaae8';
   
   p.style.margin = 0;
   p.style.marginLeft = 4;
   
   p.appendChild (headerText);
   oDiv.appendChild (p);
   this.oForm.appendChild(oDiv);
   
   return;
}

// Es el destructor del objeto
function DestructListView()
{
   var iRet = 0;
   var iIndex = 0;
   // Borramos los punteros a los DIV y a los List
   for(var ii=0; ii < this.oListView.length; ii++)
   {
      delete this.oListView[ii];
   }
   this.oListView = null;
   
   while(this.oRows.length > 0)
   {
      iRet =  this.DelRowByPos(0);
   }
   this.oForm = null;
   this.oRows = null;
}

// Borra todas las filas seleccionadas
function DelAllRowsSelected()
{
   var iRet = 0;
   var iIndex=0;
   
   while (iIndex < this.oRows.length)
   {
      if (this.oRows[iIndex].oColumns[0].oList.options.item(iIndex).selected)
      {
         iRet = this.DelRowByPos(iIndex);   
      }
      else
      {
         iIndex++;
      }
   }
   return;
}

function AddRow(Id, Text)
{
   // miramos que no existe
   if (Id == 0)
   {
      for(var ii=0; ( (ii < this.oRows.length) && (this.oRows[ii].Text != Text) ); ii++)
      {}
   }
   else
   {
      for(var ii=0; ( (ii < this.oRows.length) && (this.oRows[ii].Id != Id) ); ii++)
      {}
   }
   if (ii == this.oRows.length)
   {
      this.oRows[ii] = new RowList(ii, Id, this.oListView.length, Text, this.oListView);
      this.numRows++;
      return 1;
   }
   else
   {
      // la dejamos marcada
      this.UnSelectAllElems();
      this.oRows[ii].SelectRow();
      return -1;
   }
}
// Borra una fila con determinado Id
function DelRowById(Id)
{
   // miramos que exista
   for(var ii=0; ( (ii < this.oRows.length) && (this.oRows[ii].Id != Id) ); ii++)
   {}
   if (ii < this.oRows.length)
   {
      //Borramos todas las columnas
      for(var jj=0; jj< this.numColumns; jj++)
      {
         this.oRows[ii].DelColumn(jj);   
      }
      // Borramos la fila
      this.oRows = DeleteArray(this.oRows, ii);
      // Reordenamos los indices
      for(var kk=ii; kk < this.oRows.length; kk++)
      {
         this.oRows[kk].iIndex = kk;
      }
      this.numRows--;
      return 1;
   }
   else
   {
      return -1;
   }
}

// Borra una fila en determinada posicion del array de filas
function DelRowByPos(iIndex)
{
   if (iIndex < this.oRows.length)
   {
      //Borramos todas las columnas
      for(var jj=0; jj < this.oRows[iIndex].numColumns; jj++)
      {
         this.oRows[iIndex].DelColumn(jj);   
      }
      // Borramos la fila
      this.oRows = DeleteArray(this.oRows, iIndex);
      // Reordenamos los indices
      for(var kk=iIndex; kk < this.oRows.length; kk++)
      {
         this.oRows[kk].iIndex = kk;
      }
      this.numRows--;
      return 1;
   }
   else
   {
      return -1;
   }
}

// Devuelve el numero de elementos que hay
function getListElems()
{
   return this.oRows.length;
}

// Devuelve el indice del elemento seleccionado
function getListSelectedIndex()
{
   return this.oListView[0].oList.selectedIndex;
}

// Devuelve el numero de elementos seleccionados
function getNumRowsSelected()
{
   var iCount = 0;
   for(var ii=0; ii < this.oListView[0].oList.options.length; ii++)
   {
      if (this.oListView[0].oList.options.item(ii).selected)
      {
         iCount++;
      }
   }
   return iCount;
}

// Crea una copia de un objeto fila
function CopyRowList(oRowCopy)
{
   var oRowCopia = new RowList(oRowCopy.iIndex, oRowCopy.Id, oRowCopy.numColumns, oRowCopy.Text, oRowCopy.oListView);
   for(var ii=0; ii < oRowCopy.oColumns.length; ii++)
   {
      // Copiamos la columna
      oRowCopia.oColumns[ii] = new ColumnList(oRowCopy.oColumns[ii].oDiv, oRowCopy.oColumns[ii].oList, oRowCopy.oColumns[ii].Id, oRowCopy.oColumns[ii].Text, oRowCopy);
      // Copiamos los argumentos de la columna
      for(var jj=0; jj < oRowCopy.oColumns[ii].oColumnArgs.length; jj++)
      {
         oRowCopia.oColumns[ii].oColumnArgs[jj] = new ArgColumn(oRowCopy.oColumns[ii].oColumnArgs[jj].strName, oRowCopy.oColumns[ii].oColumnArgs[jj].strValue);
      } 
   }
   return oRowCopia;
}

// Destruye la copia de una fila
function DestructRowList(oRowCopy)
{
	for(var ii=0; ii < oRowCopy.oColumns.length; ii++){
		// Destruimos los argumentos de la columna
		for(var jj=0; jj < oRowCopy.oColumns[ii].oColumnArgs.length; jj++){
			delete oRowCopy.oColumns[ii].oColumnArgs[jj];
		}
		
		delete oRowCopy.oColumns[ii];
	}
	
	oRowCopy = null;
	
	return;
}

// Mueve la fila que esta seleccionada hacia arriba
function UpRowSelected()
{
   var iIndexSelected = this.getListSelectedIndex();
   var oRowCopia;
   
   // Solo movemos si hay una sola fila seleccionada
   if ( (this.getNumRowsSelected() == 1) && (iIndexSelected > 0) )
   {
      // Copiamos la fila
      oRowCopia = CopyRowList(this.oRows[iIndexSelected]);
      this.oRows[iIndexSelected].SetRow(this.oRows[iIndexSelected - 1].Id, this.oRows[iIndexSelected - 1].Text, this.oRows[iIndexSelected - 1].numColumns, this.oRows[iIndexSelected - 1].oColumns);
      this.oRows[iIndexSelected - 1].SetRow(oRowCopia.Id, oRowCopia.Text, oRowCopia.numColumns, oRowCopia.oColumns);
      // Seleccionamos la fila de la lista
      this.oRows[iIndexSelected].UnSelectRow();
      this.oRows[iIndexSelected-1].SelectRow();
      // Destruimos la copia
      DestructRowList(oRowCopia);
   }
}

// Mueve la fila que esta seleccionada hacia arriba
function DownRowSelected()
{
   var iIndexSelected = this.getListSelectedIndex();
   var oRowCopia;
   
   // Solo movemos si hay una sola fila seleccionada
   if ( (this.getNumRowsSelected() == 1) && (iIndexSelected < (this.numRows -1)) )
   {
      // Copiamos la fila
      oRowCopia = CopyRowList(this.oRows[iIndexSelected]);
      this.oRows[iIndexSelected].SetRow(this.oRows[iIndexSelected + 1].Id, this.oRows[iIndexSelected + 1].Text, this.oRows[iIndexSelected + 1].numColumns, this.oRows[iIndexSelected + 1].oColumns);
      this.oRows[iIndexSelected + 1].SetRow(oRowCopia.Id, oRowCopia.Text, oRowCopia.numColumns, oRowCopia.oColumns);
      // Seleccionamos la fila de la lista
      this.oRows[iIndexSelected].UnSelectRow();
      this.oRows[iIndexSelected + 1].SelectRow();
      // Destruimos la copia
      DestructRowList(oRowCopia);
   }
}

// Captura el evento de selecionar de una lista
// y se lo pasa a las otras
function SelectInOtherList(oListSel)
{
	for (var ii = 0; ii < oListSel.options.length; ii++) {
      for (var jj=0; jj < this.oListView.length ; jj++) {
         if (this.oListView[jj].oList.name != oListSel.name) {
            this.oListView[jj].oList.options.item(ii).selected = oListSel.options.item(ii).selected;
         }
      }
      
      if (oListSel.options.item(ii).selected){
         SetElemListVisible(this.oListView, (parseInt(oListSel.options.item(ii).value) + 1) * IDS_TEXT_HEIGHT);
      }
   }
   
   return;
}

// Mueve el scroll de los div para que sea visible el elemento de la lista seleccionado
function SetElemListVisible(oListV, iPos)
{
   // Miramos si seleccionamos un objeto de la lista que se nos pierde por abajo
   if ( (iPos + 15) > (oListV[0].oDiv.scrollTop + oListV[0].oDiv.style.posHeight) )
   {
      for (var jj=0; jj < oListV.length ; jj++)
      {
         oListV[jj].oDiv.scrollTop = oListV[jj].oDiv.scrollTop + IDS_TEXT_HEIGHT; 
      }
   }
   else
   {
      if ( (iPos - IDS_TEXT_HEIGHT) < (oListV[0].oDiv.scrollTop) )
      {
         var iInc = 0;
         if (oListV[0].oDiv.scrollTop >= IDS_TEXT_HEIGHT)
         {
            iInc = IDS_TEXT_HEIGHT;
         }
         for (var jj=0; jj < oListV.length ; jj++)
         {
            oListV[jj].oDiv.scrollTop = oListV[jj].oDiv.scrollTop - iInc; 
         }
      }
   }
}

// Mueve el scroll de los DIV izquierdos para que con el
// de la derecha se controlen todos
function OnMoveScrollLeft(iEventX)
{
   // miramos el div mas a la derecha
   oDivMoved = this.oListView[this.oListView.length -1].oDiv;
   
   // Miramos si estamos en la barra vertical del DIV mas a la derecha
   if (oDivMoved.clientWidth + oDivMoved.style.posLeft <= iEventX)
   {
      for(var ii=0; ii < (this.oListView.length - 1); ii++)
      {
         this.oListView[ii].oDiv.scrollTop = oDivMoved.scrollTop;
      }
   }
   return;
}

// quita la seleccion de todos los elementos
function UnSelectAllElems()
{
   for(var ii=0; ii < this.oRows.length;ii++)
   {
      this.oRows[ii].UnSelectRow();
   }
   return;
}
///////////////////////////////////////////
// FIN DEL OBJETO LISTVIEW
///////////////////////////////////////////


///////////////////////////////////////////
// OBJETO ROWLIST
///////////////////////////////////////////

// Define una fila del objeto listview. CONSTRUCTOR
function RowList(iIndex, Id, iNumCols, strText, oListView)
{
   this.iIndex = iIndex; // Inicializamos
   this.Id = Id;
   this.Text = strText;
   this.numColumns = iNumCols;
   this.oColumns = new Array();
   this.oListView = oListView;
   // Metodos
   this.SetRow = SetRow;
   this.SelectRow = SelectRow;
   this.UnSelectRow = UnSelectRow;
   this.AddColumn = AddColumn;
   this.GetColumn = GetColumn;
   this.DelColumn = DelColumn;
   this.isSelected = isSelected;     
}

// Modifica la fila de una lista
function SetRow(Id, Text, numCols, oColumns)
{
   // Modificamos todas las columnas
   if (oColumns != null)
   {
      for(var ii=0; ii < this.oColumns.length; ii++)
      {
         this.oColumns[ii].SetColumn(oColumns[ii].Id, oColumns[ii].Text, oColumns[ii].oColumnArgs);
         this.oColumns[ii].oRow = this;
      }
   }
   this.Id = Id;
   this.numCols = numCols;
   this.Text = Text;
}

// funcion que pone SELECTED una fila de la lista
function SelectRow()
{
   for(var ii=0; ii < this.oColumns.length; ii++)
   {
      this.oColumns[ii].oList.options.item(this.iIndex).selected = true;
   }
   SetElemListVisible(this.oColumns, (this.iIndex + 1) * IDS_TEXT_HEIGHT);
   return;
}

// funcion que deselecciona una fila de la lista
function UnSelectRow()
{
   for(var ii=0; ii < this.oColumns.length; ii++)
   {
      this.oColumns[ii].oList.options.item(this.iIndex).selected = false;
   }
   return;
}

// Agrega una columna a una fila de la lista
function AddColumn(Id, Text)
{
   var iIndex = this.oColumns.length;
   this.oColumns[iIndex] = new ColumnList(this.oListView[iIndex].oDiv, this.oListView[iIndex].oList, Id, Text, this);
   var oOption = document.createElement("OPTION");
   oOption.value = "" + this.iIndex + "";
   oOption.text = Text;
   this.oColumns[iIndex].oList.options.add(oOption, this.iIndex);
   // Ajustamos el tamano
   IncTamList(this.oColumns[iIndex].oList);
}

// Devuelve una columna de la lista
function GetColumn(iIndex)
{
   if (iIndex < this.oColumns.length)
   {
      return this.oColumns[iIndex];
   }
   else
   {
      return null;
   }
}

// Borra una columna de una lista
function DelColumn(iIndex)
{
	if (iIndex < this.numColumns){
		var strTxt = this.oColumns[iIndex].oList.options.item(this.iIndex).text;
      
		this.oColumns[iIndex].oList.remove(this.iIndex);
		
		// Reordenamos los values de la lista
		for(var ii=this.iIndex; ii < this.oColumns[iIndex].oList.options.length; ii++){
			this.oColumns[iIndex].oList.options.item(ii).value = "" + ii + "";
		}
      
		DecTamList(this.oColumns[iIndex].oList,strTxt); 
		delete this.oColumns[iIndex];
		
		return 1;
	}
	else {
		return -1;
	}
}

// Nos dice si una fila esta seleccionada
function isSelected()
{
   return (this.oListView[0].oList.options.item(this.iIndex).selected);
}
///////////////////////////////////////////
// FIN OBJETO ROWLIST
///////////////////////////////////////////


///////////////////////////////////////////
// OBJETO COLUMNLIST
///////////////////////////////////////////
// Define una columna del objeto listview
function ColumnList(oDiv, oList, Id, Text, oRow)
{
   this.oDiv = oDiv;
   this.oList = oList;
   this.Id = Id;
   this.Text = Text;
   this.oRow = oRow;
   this.oColumnArgs = new Array();
   // Metodos
   this.SetColumn = SetColumn;
   this.AddArgColumn = AddArgColumn;
   this.GetArgColumn = GetArgColumn;
   this.SetArgColumn = SetArgColumn;
   this.DelArgColumn = DelArgColumn;
   
}

// Modifica una columna de una fila
function SetColumn(Id, Text, oColumnArgs)
{
	this.Id = Id;
	this.Text = Text;
	
	// Modificamos el array de argumentos
	if (oColumnArgs != null) {
		for(var ii=0; ii < oColumnArgs.length; ii++) {
			this.SetArgColumn(oColumnArgs.strName, oColumnArgs.strValue);   
		}
	}
	
	var oOption = this.oList.options.item(this.oRow.iIndex);
	
	// Lo borramos de la vista (para que se refresque)
	this.oList.remove(this.oRow.iIndex);
   
	oOption.value = "" + this.oRow.iIndex + "";
	oOption.text = Text;

	this.oList.options.add(oOption, this.oRow.iIndex);
	
	// Reajustamos tamanos
	AjustarListTam(this.oList);
	
	return;
}

// Anade el argumento strName con el value strValue a la columna
function AddArgColumn(strName, strValue)
{
   // miramos que no existe
   for(var ii=0; ( (ii < this.oColumnArgs.length) && (this.oColumnArgs[ii].strName != strName) ); ii++)
   {}
   if (ii == this.oColumnArgs.length) 
   {
      this.oColumnArgs[ii] = new ArgColumn(strName, strValue);
   }
}

// Devuelve un argumento de una columna si existe
function GetArgColumn(strName)
{
   // miramos que exista
   for(var ii=0; ( (ii < this.oColumnArgs.length) && (this.oColumnArgs[ii].strName != strName) ); ii++)
   {}
   if (ii < this.oColumnArgs.length) 
   {
      return this.oColumnArgs[ii].strValue;
   }
   else
   {
      return -1;
   }
}

// Modifica un argumento de una columna si existe
function SetArgColumn(strName, strValue)
{
   // miramos que exista
   for(var ii=0; ( (ii < this.oColumnArgs.length) && (this.oColumnArgs[ii].strName != strName) ); ii++)
   {}
   if (ii < this.oColumnArgs.length) 
   {
      this.oColumnArgs[ii].strValue = strValue;
      return 1;
   }
   else
   {
      return -1;
   }
}

// Borra el argumento 'strName' si existe
function DelArgColumn(strName)
{
   // miramos que exista
   for(var ii=0; ( (ii < this.oColumnArgs.length) && (this.oColumnArgs[ii].strName != strName) ); ii++)
   {}
   if (ii < this.oColumnArgs.length) 
   {
      this.oColumnArgs = DeleteArray(this.oColumnArgs, ii);
   }
}

///////////////////////////////////////////
// FIN OBJETO COLUMNLIST
///////////////////////////////////////////


///////////////////////////////////////////
// OBJETO ARGCOLUMN
///////////////////////////////////////////
// Define un argumento de la columna
function ArgColumn(strName, strValue)
{
   this.strName = strName;
   this.strValue = strValue;
}
///////////////////////////////////////////
// OBJETO ARGCOLUMN
///////////////////////////////////////////


// devuelve el tamano horizontal del elemento mas ancho de la 
// lista, si esta no tiene elementos devuelve IDS_LIST_WIDTH
function getTamBigElement(oList)
{
   var lMaxTam = parseInt(oList.min_Width);
   for (var ii=0; ii < oList.options.length; ii++)
   {
      if ( ((oList.options.item(ii).text.length * IDS_TEXT_WIDTH) + 5) > lMaxTam )
      {
         lMaxTam = (oList.options.item(ii).text.length * IDS_TEXT_WIDTH) + 5;
      }
   }
   
   return lMaxTam;
}

// Ajusta el tamano de una lista tras ser rellenada
function AjustarListTam(oList)
{
   var iTamH = getTamBigElement(oList);
   //Ajustamos el tamano horizontal
   if (iTamH > parseInt(oList.min_Width))
   {
      oList.style.posWidth = iTamH;
   }
   // Ajustamos el tamano vertical
   if ( ((oList.options.length * IDS_TEXT_HEIGHT) + 5) > parseInt(oList.min_Height))
   {
      oList.style.posHeight = (oList.options.length * IDS_TEXT_HEIGHT) + 6;
   }
   return;
}

// Incrementa el tamano horizontal y vertical 
// de la lista si el elemento no cabe
function IncTamList(oList)
{
   var oElem = oList.options.item(oList.options.length -1);
  
   // Si el texto mas los margenes son mayores que el tamano horizontal se aumenta
   if ( ((oElem.text.length * IDS_TEXT_WIDTH) + 5) > oList.style.posWidth)
   {
      oList.style.posWidth = (oElem.text.length * IDS_TEXT_WIDTH) + 5;   
   }
   
   // Ajustamos el tamano vertical
   if ( ((oList.options.length * IDS_TEXT_HEIGHT) + 5) > parseInt(oList.min_Height))
   {
      oList.style.posHeight += IDS_TEXT_HEIGHT;
   }
   return;  
}

// Decrementa el tamano de una lista si es necesario cuando eliminamos un elemento
function DecTamList(oList, strElem)
{
   var lTamElem = 0.0;
   
   // Ajustamos el tamano horizontal
   if (oList.style.posWidth > oList.min_Width)
   {
      lTamElem = strElem.length * IDS_TEXT_WIDTH;
      // Miramos si era el elemento mas ancho (junto con lo margenes y el redondeo)
      if ( (lTamElem + 5) >= (oList.style.posWidth - 1) )
      {
         oList.style.posWidth = getTamBigElement(oList);
      }
   }
   
   // Ajustamos el tamano vertical
   if ( (oList.style.posHeight - IDS_TEXT_HEIGHT) < parseInt(oList.min_Height))
   {
      oList.style.posHeight = parseInt(oList.min_Height);
   }
   else
   {
      oList.style.posHeight -= IDS_TEXT_HEIGHT;
   }
   return; 
}

// Quita la seleccion de todos los elementos 
function NoSelectList(oList)
{
   for(var ii=0; ii < (oList.options.length); ii++)
   {
      oList.options.item(ii).selected = false;
   }
   return;   
}






