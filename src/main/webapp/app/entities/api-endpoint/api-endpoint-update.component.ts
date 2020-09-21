import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IApiEndpoint, ApiEndpoint } from 'app/shared/model/api-endpoint.model';
import { ApiEndpointService } from './api-endpoint.service';

@Component({
  selector: 'jhi-api-endpoint-update',
  templateUrl: './api-endpoint-update.component.html',
})
export class ApiEndpointUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    vendor: [],
    type: [null, [Validators.required, Validators.maxLength(50)]],
    enabled: [null, [Validators.required]],
    rate: [null, [Validators.required]],
    url: [null, [Validators.required, Validators.maxLength(200)]],
  });

  constructor(protected apiEndpointService: ApiEndpointService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ apiEndpoint }) => {
      this.updateForm(apiEndpoint);
    });
  }

  updateForm(apiEndpoint: IApiEndpoint): void {
    this.editForm.patchValue({
      id: apiEndpoint.id,
      vendor: apiEndpoint.vendor,
      type: apiEndpoint.type,
      enabled: apiEndpoint.enabled,
      rate: apiEndpoint.rate,
      url: apiEndpoint.url,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const apiEndpoint = this.createFromForm();
    if (apiEndpoint.id !== undefined) {
      this.subscribeToSaveResponse(this.apiEndpointService.update(apiEndpoint));
    } else {
      this.subscribeToSaveResponse(this.apiEndpointService.create(apiEndpoint));
    }
  }

  private createFromForm(): IApiEndpoint {
    return {
      ...new ApiEndpoint(),
      id: this.editForm.get(['id'])!.value,
      vendor: this.editForm.get(['vendor'])!.value,
      type: this.editForm.get(['type'])!.value,
      enabled: this.editForm.get(['enabled'])!.value,
      rate: this.editForm.get(['rate'])!.value,
      url: this.editForm.get(['url'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IApiEndpoint>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }
}
