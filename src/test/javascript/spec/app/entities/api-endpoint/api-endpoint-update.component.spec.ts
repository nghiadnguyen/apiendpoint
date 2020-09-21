import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { ApiendpointTestModule } from '../../../test.module';
import { ApiEndpointUpdateComponent } from 'app/entities/api-endpoint/api-endpoint-update.component';
import { ApiEndpointService } from 'app/entities/api-endpoint/api-endpoint.service';
import { ApiEndpoint } from 'app/shared/model/api-endpoint.model';

describe('Component Tests', () => {
  describe('ApiEndpoint Management Update Component', () => {
    let comp: ApiEndpointUpdateComponent;
    let fixture: ComponentFixture<ApiEndpointUpdateComponent>;
    let service: ApiEndpointService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ApiendpointTestModule],
        declarations: [ApiEndpointUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(ApiEndpointUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ApiEndpointUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ApiEndpointService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ApiEndpoint(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new ApiEndpoint();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
