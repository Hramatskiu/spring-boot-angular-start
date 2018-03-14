import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';

import { ClusterService } from './cluster.service';
import { ClusterState } from './cluster-state.model';
import { YarnApplication } from '../service/yarn/application.model';
import { YarnApplicationService } from '../service/yarn/yarn-application.service';
import { ErrorReportingService } from '../shared/error/error-reporting.service';

@Component({
  selector: 'cluster-info',
  templateUrl: './cluster.component.html',
})
export class ClusterComponent implements OnInit {
  clusterState: ClusterState;
  yarnApps: YarnApplication[];

  constructor( private clusterService: ClusterService, private router: Router,
     private route: ActivatedRoute, private yarnApplicationService: YarnApplicationService, private errorReportingService: ErrorReportingService ) {
  }

  isShowServiceActionAllow(name: string){
    return name == "hbase" || name == "hive"
  }

  restartService( serviceName: string ) {

  }

  ngOnInit() {
    this.clusterState = new ClusterState();
    this.yarnApps = new Array<YarnApplication>();
    this.router.onSameUrlNavigation = 'ignore';
    this.route.paramMap.subscribe( (params: ParamMap) => {
      this.errorReportingService.clearError();
      this.clusterService.getClusterState( params.get('id') ).subscribe(
        data => this.clusterState = data,
        error => this.errorReportingService.reportError( error.error.message )
      );
      this.yarnApplicationService.getYarnApps( params.get('id') ).subscribe(
        data => this.yarnApps = data
      )
    });
  }
}
