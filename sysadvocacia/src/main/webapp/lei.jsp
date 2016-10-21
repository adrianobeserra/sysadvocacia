<%-- 
    Document   : lei
    Created on : 19/10/2016, 09:59:53
    Author     : Ewerton
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<c:import url="/cabecalho.jsp" />
<div class="container-page">


<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>sysadvocacia | Cadastro de Leis</title>
        <link rel="stylesheet" type="text/css" href="http://www.jeasyui.com/easyui/themes/default/easyui.css">
        <link rel="stylesheet" type="text/css" href="http://www.jeasyui.com/easyui/themes/icon.css">
        <link rel="stylesheet" type="text/css" href="http://www.jeasyui.com/easyui/themes/color.css">
        <link rel="stylesheet" type="text/css" href="http://www.jeasyui.com/easyui/demo/demo.css">
        <script type="text/javascript" src="http://code.jquery.com/jquery-1.6.min.js"></script>
        <script type="text/javascript" src="http://www.jeasyui.com/easyui/jquery.easyui.min.js"></script>
    </head>
    <body>
        <h2>Cadastro de Leis</h2>
        <p>Listar, Cadastrar, Alterar e Remover Leis do Sistema</p>
        <table id="dg" title="Clientes" class="easyui-datagrid" style="width:800px;height:400px"
               url="ListarLeis"
               toolbar="#toolbar" pagination="true"
               rownumbers="true" fitColumns="true" singleSelect="true">
            <thead>
                <tr>
                    <th field="codigo" width="50">Código</th>
                    <th field="descricao" width="150">Descrição</th>
                    <th field="tipo" width="100">Tipo</th>
                    <th field="capitulo" width="80">Capitulo</th>
                    <th field="artigo" width="200">Artigo</th>
                   
                </tr>
            </thead>
        </table>
        <div id="toolbar">
            <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newLaw()">Nova Lei</a>
            <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editLaw()">Alterar Lei</a>
            <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="destroyLaw()">Remover Lei</a>
        </div>

        <div id="dlg" class="easyui-dialog" style="width:400px;height:280px;padding:10px 20px"
             closed="true" buttons="#dlg-buttons">
            <div class="ftitle">Informações da Lei</div>
            <form id="fm" method="post"  action="SalvarLei" novalidate>
                <input type="hidden" name="codigo"/>
                <div class="fitem">
                    <label>Descrição</label>
                    <input name="descricao" class="easyui-textbox" required="true">
                </div>
                <div class="fitem">
                    <label>Tipo</label>
                    <input name="tipo" class="easyui-textbox" required="true">
                </div>
                <div class="fitem">
                    <label>Artigo</label>
                    <input name="artigo" class="easyui-textbox">
                </div>
                <div class="fitem">
                    <label>Capitulo</label>
                    <input name="capitulo" class="easyui-textbox" >
                </div>
            </form>
        </div>
        <div id="dlg-buttons">
            <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveLaw()" style="width:90px">Salvar</a>
            <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')" style="width:90px">Cancelar</a>
        </div>
        
        <script type="text/javascript">
            var url;
            function newLaw() {
                $('#dlg').dialog('open').dialog('setTitle', 'Nova Lei');
                $('#fm').form('clear');
                url = 'SalvarLei';
            }
            function editLaw() {
                var row = $('#dg').datagrid('getSelected');
                if (row) {
                    $('#dlg').dialog('open').dialog('setTitle', 'Alterar Lei');
                    $('#fm').form('load', row);
                    url = 'SalvarLei';
                }
            }
            function saveLaw() {
                $('#fm').form('submit', {
                    url: url,
                    onSubmit: function () {
                        return $(this).form('validate');
                    },
                    success: function (result) {
                        var result = eval('(' + result + ')');
                        if (result.errorMsg) {
                            $.messager.show({
                                title: 'Error',
                                msg: result.errorMsg
                            });
                        } else {
                            $('#dlg').dialog('close');		// close the dialog
                            $('#dg').datagrid('reload');	// reload the user data
                        }
                    }
                });
            }
            
            function destroyLaw() {
                var row = $('#dg').datagrid('getSelected');
                if (row) {
                    $.messager.confirm('Confirmar', 'Deseja realmente remover esta Lei?', function (r) {
                        if (r) {
                            $.post('RemoverLei', {id: row.codigo}, function (result) {
                                if (result.success) {
                                    $('#dg').datagrid('reload');	// reload the user data
                                } else {
                                    $.messager.show({// show error message
                                        title: 'Error',
                                        msg: result.errorMsg
                                    });
                                }
                            },'json');
                        }
                    });
                }
            }
        </script>
        <style type="text/css">
            #fm{
                margin:0;
                padding:10px 30px;
            }
            .ftitle{
                font-size:14px;
                font-weight:bold;
                padding:5px 0;
                margin-bottom:10px;
                border-bottom:1px solid #ccc;
            }
            .fitem{
                margin-bottom:5px;
            }
            .fitem label{
                display:inline-block;
                width:80px;
            }
            .fitem input{
                width:160px;
            }
        </style>
</div>
<c:import url="/rodape.jsp" />