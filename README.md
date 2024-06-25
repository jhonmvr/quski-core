
##CREACION DE NUEVOS CAMBIOS
Descargar la ultima version de master 
```
 git checkout master
 git -c http.sslVerify=false pull 
```

Crear un branch local
```
 git checkout -b BRANCH_NAME
 Ejemplo:
 git checkout -b lt/sprint-20/descripcion-cambio 
```

Terminado los cambios hacer commit y psh crear un Merge request, mismo que debe ser validado por lider tecnico
```
 git -c http.sslVerify=false push
 Solo la primera vez para generar la realcion con reposotorio remoto
 git -c http.sslVerify=false  push --set-upstream origin lt/merge-master/documentacion
 ```
Este comando genera un link, como el siguente ejemplo:
```
git -c http.sslVerify=false   push --set-upstream origin lt/merge-master/documentacion
Total 0 (delta 0), reused 0 (delta 0)
remote:
remote: To create a merge request for lt/merge-master/documentacion, visit:
remote:   https://service.relative-engine.com:8943/quski/quski-oro-core/-/merge_requests/new?merge_request%5Bsource_branch%5D=lt%2Fmerge-master%2Fdocumentacion
remote:
To https://service.relative-engine.com:8943/quski/quski-oro-core.git
 * [new branch]        lt/merge-master/documentacion -> lt/merge-master/documentacion
Branch 'lt/merge-master/documentacion' set up to track remote branch 'lt/merge-master/documentacion' from 'origin'.

```
 Al hacer click en el link abrira gitlab para que creen el Merge request
 

La branch master esta protegida solo pueden hacer commit en esa branch lider tecnico