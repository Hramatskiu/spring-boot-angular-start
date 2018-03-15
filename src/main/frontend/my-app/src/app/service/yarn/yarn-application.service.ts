import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { YarnApplication } from './application.model';

@Injectable()
export class YarnApplicationService {
  constructor( private http: HttpClient ) {  }

  getYarnApps( clusterName: string ) {
    return this.http.get<Array<YarnApplication>>( "http://localhost:8888/getApplicationList", { params: { "clusterName": clusterName } } )
  }
}
