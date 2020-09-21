import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IApiEndpoint } from 'app/shared/model/api-endpoint.model';
import { ApiEndpointService } from './api-endpoint.service';

@Component({
  templateUrl: './api-endpoint-delete-dialog.component.html',
})
export class ApiEndpointDeleteDialogComponent {
  apiEndpoint?: IApiEndpoint;

  constructor(
    protected apiEndpointService: ApiEndpointService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.apiEndpointService.delete(id).subscribe(() => {
      this.eventManager.broadcast('apiEndpointListModification');
      this.activeModal.close();
    });
  }
}
