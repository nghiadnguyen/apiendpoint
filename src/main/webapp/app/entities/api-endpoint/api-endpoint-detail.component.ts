import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IApiEndpoint } from 'app/shared/model/api-endpoint.model';

@Component({
  selector: 'jhi-api-endpoint-detail',
  templateUrl: './api-endpoint-detail.component.html',
})
export class ApiEndpointDetailComponent implements OnInit {
  apiEndpoint: IApiEndpoint | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ apiEndpoint }) => (this.apiEndpoint = apiEndpoint));
  }

  previousState(): void {
    window.history.back();
  }
}
