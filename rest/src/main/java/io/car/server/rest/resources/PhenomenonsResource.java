/**
 * Copyright (C) 2013  Christian Autermann, Jan Alexander Wirwahn,
 *                     Arne De Wall, Dustin Demuth, Saqib Rasheed
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package io.car.server.rest.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import io.car.server.core.entities.Phenomenon;
import io.car.server.core.entities.Phenomenons;
import io.car.server.core.exception.PhenomenonNotFoundException;
import io.car.server.rest.AbstractResource;
import io.car.server.rest.MediaTypes;
import io.car.server.rest.auth.Authenticated;

/**
 * @author Christian Autermann <c.autermann@52north.org>
 * @author Jan Wirwahn <jan.wirwahn@wwu.de>
 */
public class PhenomenonsResource extends AbstractResource {
    public static final String PHENOMENON_PATH = "{phenomenon}";
    @GET
    @Produces(MediaTypes.PHENOMENONS)
    public Phenomenons get() {
        return getService().getAllPhenomenons();
    }

    @POST
    @Authenticated
    @Consumes(MediaTypes.PHENOMENON_CREATE)
    public Response create(Phenomenon phenomenon) {
        return Response.created(getUriInfo().getRequestUriBuilder().path(getService().createPhenomenon(phenomenon).getName()).build()).build();
    }
    
    @Path(PHENOMENON_PATH)
    public PhenomenonResource phenomenon(@PathParam("phenomenon") String id) throws PhenomenonNotFoundException {
        return getResourceFactory().createPhenomenonResource(getService().getPhenomenonByName(id));
    }
}