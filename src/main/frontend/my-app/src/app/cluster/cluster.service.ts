import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { Cluster } from '../shared/cluster/cluster.model';

@Injectable()
export class ClusterService {
  constructor(private http: HttpClient) {  }

  getClusters() {
    return this.http.get<Array<Cluster>>("http://localhost:8888/clusters");
  }

  getClusterState( clusterName: string ) {
    return this.http.get("http://localhost:8888/getClusterStatus", { params: { "clusterName": clusterName } });
  }
}
