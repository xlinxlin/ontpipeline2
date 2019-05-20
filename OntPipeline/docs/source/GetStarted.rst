Get Started
===========
Installation
_______________________________
Anaconda Installation
^^^^^^^^^^^^^^^^^^^^^
Installing on Linux https://docs.anaconda.com/anaconda/install/linux/

Guppy3.0.3 Installation
^^^^^^^^^^^^^^^^^^^^^^^

Guppy is a data processing toolkit that contains the Oxford Nanopore Technologies' basecalling algorithms, and several bioinformatic post-processing features.

.. code-block:: bash

  wget https://mirror.oxfordnanoportal.com/software/analysis/ont-guppy-cpu_3.0.3_linux64.tar.gz
  tar -xf ont-guppy-cpu_3.0.3_linux64.tar.gz
  sudo mv ont-guppy-cpu_3.0.3_linux64 /opt/ont-guppy-cpu_3.0.3

Porechop Installation
^^^^^^^^^^^^^^^^^^^^^

Porechop is a tool for finding and removing adapters from Oxford Nanopore reads.

.. code-block:: bash
   
   /opt/anaconda3/bin/conda create -n porechop
   source /opt/anaconda3/bin/activate porechop
   /opt/anaconda3/bin/conda install -c bioconda porechop
   conda deactivate

NanoStat Installation
^^^^^^^^^^^^^^^^^^^^^

NanoStat calculates various statistics from a long read sequencing dataset in fastq, bam or albacore sequencing summary format.

.. code-block:: bash

   /opt/anaconda3/bin/conda create -n nanostat
   source /opt/anaconda3/bin/activate nanostat
   /opt/anaconda3/bin/conda install -c bioconda nanostat
   conda deactivate

NanoFilt Installation
^^^^^^^^^^^^^^^^^^^^^

NanoFilt filters and trims long read sequencing data.

.. code-block:: bash

   /opt/anaconda3/bin/conda create -n nanofilt
   source /opt/anaconda3/bin/activate nanofilt
   /opt/anaconda3/bin/conda install -c bioconda nanofilt
   conda deactivate


Unicycler Installation
^^^^^^^^^^^^^^^^^^^^^^

Unicycler is an assembly pipeline for bacterial genomes.

.. code-block:: bash
   
   /opt/anaconda3/bin/conda create -n unicycler
   source /opt/anaconda3/bin/activate unicylcer
   /opt/anaconda3/bin/conda install -c bioconda unicycler
   /opt/anaconda3/bin/conda install -c bioconda bcftools # for .vcf file
   conda deactivate

BUSCO Installation
^^^^^^^^^^^^^^^^^^

BUSCO v3 provides quantitative measures for the assessment of genome assembly, gene set, and transcriptome completeness, based on evolutionarily-informed expectations of gene content from near-universal single-copy orthologs selected from OrthoDB v9.

.. code-block:: bash
   
   /opt/anaconda3/bin/conda create -n busco
   source /opt/anaconda3/bin/activate busco
   /opt/anaconda3/bin/conda install -c bioconda busco
   conda deactivate

BWA Installation
^^^^^^^^^^^^^^^^

BWA is a software package for mapping low-divergent sequences against a large reference genome. 

.. code-block:: bash
   
   /opt/anaconda3/bin/conda create -n bwa
   source /opt/anaconda3/bin/activate bwa
   /opt/anaconda3/bin/conda install -c bioconda bwa
   conda deactivate

Seqtk Installation
^^^^^^^^^^^^^^^^^^

Seqtk is a fast and lightweight tool for processing sequences in the FASTA or FASTQ format. 

.. code-block:: bash
   
   /opt/anaconda3/bin/conda create -n seqtk
   source /opt/anaconda3/bin/activate seqtk
   /opt/anaconda3/bin/conda install -c bioconda seqtk
   conda deactivate

