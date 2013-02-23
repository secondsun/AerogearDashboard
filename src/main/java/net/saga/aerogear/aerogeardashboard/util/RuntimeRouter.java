/**
 * JBoss, Home of Professional Open Source Copyright Red Hat, Inc., and
 * individual contributors by the
 *
 * @authors tag. See the copyright.txt in the distribution for a full listing of
 * individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at http://www.apache.org/licenses/LICENSE-2.0 Unless required by
 * applicable law or agreed to in writing, software distributed under the
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS
 * OF ANY KIND, either express or implied. See the License for the specific
 * language governing permissions and limitations under the License.
 */
package net.saga.aerogear.aerogeardashboard.util;

import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jboss.aerogear.controller.router.DefaultRouter;
import org.jboss.aerogear.controller.router.Route;
import org.jboss.aerogear.controller.router.RouteBuilder;
import org.jboss.aerogear.controller.router.RouteContext;
import org.jboss.aerogear.controller.router.RouteProcessor;
import org.jboss.aerogear.controller.router.RoutingModule;
import org.jboss.aerogear.controller.util.RequestUtils;

import static org.jboss.aerogear.controller.util.RequestUtils.extractMethod;
import static org.jboss.aerogear.controller.util.RequestUtils.extractPath;
import static org.jboss.aerogear.controller.util.RequestUtils.extractAcceptHeader;

/**
 *
 * @author summers
 */
@Alternative
public class RuntimeRouter extends DefaultRouter {

    private static org.jboss.aerogear.controller.router.Routes runtimeRoutes = org.jboss.aerogear.controller.router.Routes.from(new ArrayList<RouteBuilder>());
    List<RouteBuilder> builders = new ArrayList<>();
    private RouteProcessor routeProcessor;

    @Inject
    public RuntimeRouter(Instance<RoutingModule> instance, RouteProcessor routeProcessor) {
        super(instance, routeProcessor);
        this.routeProcessor = routeProcessor;
    }

    @Override
    public boolean hasRouteFor(HttpServletRequest httpServletRequest) {
        if (!hasRuntimeRoute(httpServletRequest)) {
            return super.hasRouteFor(httpServletRequest);
        } else {
            return true;
        }
    }

    @Override
    public void dispatch(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException {
        if (hasRuntimeRoute(request)) {
            try {
                String requestPath = RequestUtils.extractPath(request);
                Route route = runtimeRoutes.routeFor(extractMethod(request), requestPath, extractAcceptHeader(request));
                this.routeProcessor.process(new RouteContext(route, requestPath, request, response, runtimeRoutes));
            } catch (Exception e) {
                throw new ServletException(e.getMessage(), e);
            }
        } else {
            super.dispatch(request, response, chain);
        }
    }

    private boolean hasRuntimeRoute(HttpServletRequest request) {
        return runtimeRoutes.hasRouteFor(extractMethod(request), extractPath(request), extractAcceptHeader(request));
    }

    public void addRoute(RouteBuilder route) {
        builders.add(route);
        runtimeRoutes = org.jboss.aerogear.controller.router.Routes.from(builders);
    }
}
