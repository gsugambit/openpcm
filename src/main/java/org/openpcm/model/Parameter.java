package org.openpcm.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.openpcm.utils.ObjectUtil;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@NamedEntityGraph(name = "Parameter.attributes", attributeNodes = @NamedAttributeNode("attributes"))
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "parameter")
public class Parameter {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "parameter_id")
    private Long id;

    @NotNull
    private String name;

    private String description;

    private String uom;

    private String value;

    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;

    private String utcOffset;

    @ElementCollection
    @CollectionTable(name = "parameter_attributes", joinColumns = @JoinColumn(name = "parameter_id"))
    private Set<Attribute> attributes = new HashSet<>();

    @Override
    public String toString() {
        return ObjectUtil.print(this);
    }
}
