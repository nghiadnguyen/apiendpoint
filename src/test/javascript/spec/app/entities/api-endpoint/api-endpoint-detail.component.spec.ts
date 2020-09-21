import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ApiendpointTestModule } from '../../../test.module';
import { ApiEndpointDetailComponent } from 'app/entities/api-endpoint/api-endpoint-detail.component';
import { ApiEndpoint } from 'app/shared/model/api-endpoint.model';

describe('Component Tests', () => {
  describe('ApiEndpoint Management Detail Component', () => {
    let comp: ApiEndpointDetailComponent;
    let fixture: ComponentFixture<ApiEndpointDetailComponent>;
    const route = ({ data: of({ apiEndpoint: new ApiEndpoint(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ApiendpointTestModule],
        declarations: [ApiEndpointDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(ApiEndpointDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ApiEndpointDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load apiEndpoint on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.apiEndpoint).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
