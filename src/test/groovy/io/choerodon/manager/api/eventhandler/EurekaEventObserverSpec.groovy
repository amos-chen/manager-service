package io.choerodon.manager.api.eventhandler

import io.choerodon.eureka.event.EurekaEventPayload
import io.choerodon.manager.app.service.ActuatorRefreshService
import io.choerodon.manager.app.service.DocumentService
import io.choerodon.manager.app.service.RouteService
import io.choerodon.manager.app.service.SwaggerRefreshService
import spock.lang.Specification

class EurekaEventObserverSpec extends Specification {
    def "test receiveUpEvent"() {
        given: 'mock RegisterInstanceService'
        def service = Mock(DocumentService) {
            fetchSwaggerJsonByIp(_) >> '{}'
        }
        def service1 = Mock(SwaggerRefreshService)
        def service2 = Mock(RouteService)
        def actuatorRefreshService = Mock(ActuatorRefreshService)
        def observer = new EurekaEventObserver(service, service1, actuatorRefreshService, service2)

        when:
        observer.receiveUpEvent(new EurekaEventPayload())
        then:
        1 * service1.updateOrInsertSwagger(_, _)

    }
}
