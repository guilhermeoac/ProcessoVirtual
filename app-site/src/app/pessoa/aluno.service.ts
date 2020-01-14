import {HttpClient} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Table} from 'primeng/primeng';
import {Observable} from 'rxjs';
import {Aluno} from './aluno.model';
import {RequestUtil} from '../util/request-util';

@Injectable({
  providedIn: 'root'
})
export class AlunoService {
  resourceUrl = 'http://localhost:8090/api/aluno/';
  constructor( private httpClient: HttpClient) {
  }

  getDeclaracaoFilter(alunoFilter: Aluno,  datatable: Table): Observable<any> {
    return this.httpClient.post(this.resourceUrl + 'filter/' , alunoFilter, {params : RequestUtil.getRequestParams(datatable) });
  }

  findById( id: number): Observable<any> {
    return this.httpClient.get (`${this.resourceUrl}${id}`);
  }

  findDropDown(): Observable<any> {
    return this.httpClient.get (`${this.resourceUrl}`);
  }

  save( registro: any ): Observable<any> {
    return this.httpClient.post( this.resourceUrl, registro );
  }

  excluir( id: number ): Observable<any>{
    return this.httpClient.delete(this.resourceUrl + id);
  }
}
