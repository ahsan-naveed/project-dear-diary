ARG DISTRO=bionic
FROM ubuntu:${DISTRO}

ARG DISTRO
ARG LLVM_VERSION="8"
ARG CMAKE_VER="3.16"
ARG CMAKE_BUILD="2"
ARG bcc_ref="v0.12.0"
ARG bcc_org="iovisor"

ENV LLVM_VERSION=$LLVM_VERSION
ENV CMAKE_VER=${CMAKE_VER}
ENV CMAKE_BUILD="${CMAKE_BUILD}"
ENV DISTRO=${DISTRO}

RUN apt-get update && apt-get install -y curl gnupg &&\
    llvmRepository="\n\
deb http://apt.llvm.org/${DISTRO}/ llvm-toolchain-${DISTRO} main\n\
deb-src http://apt.llvm.org/${DISTRO}/ llvm-toolchain-${DISTRO} main\n\
deb http://apt.llvm.org/${DISTRO}/ llvm-toolchain-${DISTRO}-${LLVM_VERSION} main\n\
deb-src http://apt.llvm.org/${DISTRO}/ llvm-toolchain-${DISTRO}-${LLVM_VERSION} main\n" &&\
    echo $llvmRepository >> /etc/apt/sources.list && \
    curl -L https://apt.llvm.org/llvm-snapshot.gpg.key | apt-key add -

RUN apt-get update && apt-get install -y \
      bison \
      binutils-dev \
      build-essential \
      flex \
      g++ \
      git \
      libelf-dev \
      zlib1g-dev \
      libiberty-dev \
      libbfd-dev \
      libedit-dev \
      clang-${LLVM_VERSION} \
      libclang-${LLVM_VERSION}-dev \
      libclang-common-${LLVM_VERSION}-dev \
      libclang1-${LLVM_VERSION} \
      llvm-${LLVM_VERSION} \
      llvm-${LLVM_VERSION}-dev \
      llvm-${LLVM_VERSION}-runtime \
      libllvm${LLVM_VERSION} \
      systemtap-sdt-dev \
      python3 \
      quilt

# Install from source if using xenial due to incompatibility with older cmake
RUN if [ "${DISTRO}" = "xenial" ]; then \
    apt-get install -y make python libssl-dev \
    && version=${CMAKE_VER} \
    && build=${CMAKE_BUILD} \
    && mkdir -p /tmp \
    && cd /tmp \
    && curl -OL https://cmake.org/files/v${version}/cmake-${version}.${build}.tar.gz \
    && tar -xzvf cmake-${CMAKE_VER}.$build.tar.gz \
    && cd cmake-${CMAKE_VER}.$build/ \
    && ./bootstrap \
    && make -j$(nproc) \
    && make install \
    ; else \
    apt install -y cmake \
    ; fi

# Build BCC and install static libs
RUN mkdir -p /src && git clone https://github.com/$bcc_org/bcc /src/bcc

RUN cd /src/bcc && git checkout $bcc_ref && git submodule update && \
    mkdir build && cd build && \
    cmake -DCMAKE_INSTALL_PREFIX=/usr/local ../ && make -j$(nproc) && \
    make install &&  mkdir -p /usr/local/lib && \
    cp src/cc/libbcc.a /usr/local/lib/libbcc.a && \
    cp src/cc/libbcc-loader-static.a /usr/local/lib/libbcc-loader-static.a && \
    cp ./src/cc/libbcc_bpf.a /usr/local/lib/libbpf.a

COPY build.sh /build.sh
RUN chmod 755 /build.sh
ENTRYPOINT ["bash", "/build.sh"]
