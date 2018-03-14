import {Component} from '@angular/core';
import { OnInit } from '@angular/core';

import { Cluster } from '../../cluster/cluster.model';
import { ClusterService } from '../../../cluster/cluster.service';
import { RouteService } from './route.service';

@Component({
  selector: 'sidebar',
  templateUrl: './sidebar.component.html'
})
export class SideBarComponent implements OnInit{
  clusters: Cluster[];
  tempCluster: Cluster;

  constructor( private clusterService: ClusterService, private routeService: RouteService ) {}

  ngOnInit() {
    this.clusterService.getClusters().subscribe(
      data => this.clusters = data
    );
    this.tempCluster = new Cluster();
  }

  checkClusterHealth( clusterName: string ) {
    this.routeService.routeToHealthCheck( clusterName );
  }
}
