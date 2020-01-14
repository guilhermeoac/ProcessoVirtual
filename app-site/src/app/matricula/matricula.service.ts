import {HttpClient} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Table} from 'primeng/primeng';
import {Observable} from 'rxjs';
import {Matricula} from './matricula.model';
import {RequestUtil} from '../util/request-util';
import {MatriculaFilter} from './matricula-filter.model';

@Injectable({
  providedIn: 'root'
})
export class MatriculaService {
  resourceUrl = 'http://localhost:8090/api/matricula/';
  constructor( private httpClient: HttpClient) {
  }

  getDeclaracaoFilter(matriculaFilter: MatriculaFilter,  datatable: Table): Observable<any> {
    return this.httpClient.post(this.resourceUrl + 'filter/' , matriculaFilter, {params : RequestUtil.getRequestParams(datatable) });
  }

  findById( id: number): Observable<any> {
    return this.httpClient.get (`${this.resourceUrl}${id}`);
  }

  save( registro: any ): Observable<any> {
    return this.httpClient.post( this.resourceUrl, registro );
  }

  excluir( id: number ): Observable<any>{
    return this.httpClient.delete(this.resourceUrl + id);
  }
}
